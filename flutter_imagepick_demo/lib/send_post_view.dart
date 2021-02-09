import 'dart:typed_data';

import 'package:dio/dio.dart';
import 'package:flutter/material.dart';
import 'package:multi_image_picker/multi_image_picker.dart';
import 'package:http_parser/http_parser.dart';
import 'http_utils.dart';


class SendPostView extends StatefulWidget {
  @override
  _SendPostViewState createState() => _SendPostViewState();
}

class _SendPostViewState extends State<SendPostView> {
  var _imgPath;

  TextEditingController _controller;

  List<Asset> resultList;

  @override
  void initState() {
    // TODO: implement initState
    super.initState();
    _controller = TextEditingController();
    resultList = List();
  }

  @override
  void dispose() {
    // TODO: implement dispose
    super.dispose();
    _controller.dispose();
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
    title: Text('发动态'),
    actions: [
      IconButton(
          icon: Icon(Icons.send),
          onPressed: () async {
            if (resultList.length == 0 &&
                (_controller.text == null ||
                    _controller.text.isEmpty)) {
              Toast.popToast('内容不能为空');
              return;
            }

            if(resultList.length!=0) {
              List<MultipartFile> files = List();
              for (int i = 0; i < resultList.length; i++) {
                // 获取 ByteData
                ByteData byteData = await resultList[i].getByteData();
                List<int> imageData = byteData.buffer.asUint8List();

                MultipartFile multipartFile = MultipartFile.fromBytes(
                  imageData,
                  // 文件名
                  filename: 'some-file-name.jpg',
                  // 文件类型
                  contentType: MediaType("image", "jpg"),
                );

                files.add(multipartFile);
              }

              var params = Params().getParams();
              FormData formData = FormData.fromMap({
                // 后端接口的参数名称
                "image": files,
                'token': params['token'],
                'profile': params['profile']
              });

              String urls = "";

              HttpUtils.instance.post("/upload", formData,
                  success: (response) {
                    if (response['code'] == 200) {
                      List list = response['data'];

                      for (var i = 0; i < list.length; i++) {
                        if (i != 0) urls += "￥";
                        urls += list[i];
                      }

                      params['picture_urls'] = urls;
                      params['content'] = _controller.text;
                      if (Platform.isAndroid) {
                        params['froms'] = "Android";
                      } else {
                        params['froms'] = "IOS";
                      }
                      params['uid'] = Global.profile.user.uid;
                      HttpUtils.instance
                          .post(Urls.POST, params, success: (response) {

                        Toast.popToast(response['msg']);

                        Navigator.of(context).pop();

                      });
                    } else {
                      Toast.popToast('网络出问题，请稍后重试');
                    }
                  });
            }
            else {
              var params = Params().getParams();
              params['content'] = _controller.text;
              params['uid'] = Global.profile.user.uid;
              if (Platform.isAndroid) {
                params['froms'] = "Android";
              } else {
                params['froms'] = "IOS";
              }
              HttpUtils.instance
                  .post(Urls.POST, params, success: (response) {

                Toast.popToast(response['msg']);

                Navigator.of(context).pop();

              });
            }


          })
    ],
      ),
      body: Container(
    padding: EdgeInsets.all(5),
    child: ListView(
      children: [
        TextField(
          controller: _controller,
          decoration: InputDecoration(
              border: OutlineInputBorder(), hintText: '说点什么……'),
          maxLines: 7,
        ),
        Row(
          children: [
            RaisedButton(
              onPressed: () {
                uploadImages();
              },
              child: Text('选择图片'),
            ),
          ],
        ),
        Container(
          width: double.infinity,
          height: 1000,
          child: GridView.builder(
            padding: EdgeInsets.all(0),
            gridDelegate: SliverGridDelegateWithFixedCrossAxisCount(
                crossAxisCount: 3,
                crossAxisSpacing: 2,
                mainAxisSpacing: 2),
            itemBuilder: (BuildContext context, int index) {
              return _createGridViewItem(
                  AssetThumb(
                    asset: resultList[index],
                    width: 300,
                    height: 300,
                  ),
                  index);
            },
            itemCount: resultList.length,
          ),
        )
      ],
    ),
      ),
    );
  }

  _createGridViewItem(widget, index) {
    return Container(
      height: 100,
      width: 100,
      padding: EdgeInsets.all(0),
      margin: EdgeInsets.all(0),
      child: Stack(
        children: [
          widget,
          Positioned(
            top: 0,
            right: 0,
            child: GestureDetector(
              onTap: () {
                setState(() {
                  resultList.removeAt(index);
                });
              },
              child: Icon(
                Icons.close,
                color: Colors.grey,
              ),
            ),
          )
        ],
      ),
    );
  }



  // 选择照片并上传
  Future<void> uploadImages() async {
    if (resultList == null) {
      resultList = List<Asset>();
    }
    try {
      var tmp = await MultiImagePicker.pickImages(
        selectedAssets: resultList,
        // 选择图片的最大数量
        maxImages: 9,
        // 是否支持拍照
        enableCamera: true,
        materialOptions: MaterialOptions(
          // 显示所有照片，值为 false 时显示相册
            startInAllView: false,
            allViewTitle: '所有照片',
            actionBarColor: '#2196F3',
            textOnNothingSelected: '没有选择照片'),
      );
      if (tmp.length != 0) {
        print("hjhhhhhhhhhhhhhhhhhhhhhhh");
        resultList = tmp;
        setState(() {});
      }
    } on Exception catch (e) {
      e.toString();
    }
  }


}
