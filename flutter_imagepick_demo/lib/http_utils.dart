import 'package:dio/dio.dart';
import 'package:dio_log/interceptor/dio_log_interceptor.dart';

typedef Success = void Function(dynamic json);
typedef Fail = void Function(String reason, int code);
typedef After = void Function();

class HttpUtils {
  static Dio _dio;
  static HttpUtils _https = HttpUtils();



  static HttpUtils get instance => _https;

  HttpUtils() {
    if (_dio == null) {
      _dio = createDio();
    }
  }

  Dio createDio() {
    var dio = Dio(BaseOptions(
      connectTimeout: 30000,
      receiveTimeout: 30000,
      sendTimeout: 30000,
      responseType: ResponseType.json,
    ));
    dio.interceptors.add(DioLogInterceptor());

    return dio;
  }

  Future<void> get(String uri, Map<String, dynamic> params,
      {Success success, Fail fail, After after}) {
    _dio.get(uri, queryParameters: params).then((response) {
      if (response.statusCode == 200) {
        if (success != null) {
          success(response.data);
        }
      } else {
        if (fail != null) {
          fail(response.statusMessage, response.statusCode);
        }
      }

      if (after != null) {
        after();
      }
    });
    return Future.value();
  }


  Future<void> post(String uri, data,
      {params,Success success, Fail fail, After after}) {
    _dio.post(uri, data: data,queryParameters: params).then((response) {
      if (response.statusCode == 200) {
        if (success != null) {
          success(response.data);
        }
      } else {
        if (fail != null) {
          fail(response.statusMessage, response.statusCode);
        }
      }

      if (after != null) {
        after();
      }
    });
    return Future.value();
  }

  Future<void> delete(String uri, data,
      {params,Success success, Fail fail, After after}) {
    _dio.delete(uri, data: data,queryParameters: params).then((response) {
      if (response.statusCode == 200) {
        if (success != null) {
          success(response.data);
        }
      } else {
        if (fail != null) {
          fail(response.statusMessage, response.statusCode);
        }
      }

      if (after != null) {
        after();
      }
    });
    return Future.value();
  }
}