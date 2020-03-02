using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
using System.Collections;
namespace Calculator
{
    public partial class Form1 : Form
    {
        private ArrayList list = new ArrayList();
        private bool op_flag = false;
        private bool num_flag = true;
        private bool sing_op_flag = false;
        private bool dot_flag = true;
        private bool is_clear = true;
        public Form1()
        {
            InitializeComponent();
            
        }

        private void input_Number(object sender, EventArgs e)
        {
            if (is_clear) input_Delete(this.btn_clsall, e);
            if (num_flag)
            {
                if (sender == btn_dot)
                {
                    if (!dot_flag) return;
                    dot_flag = false;
                    if (this.lab_num.Text.Length > 0)
                        this.lab_num.Text += ".";
                    else
                        this.lab_num.Text += "0.";
                }
                else
                {
                    if (this.lab_num.Text.Length < 2 && this.lab_num.Text[0] == '0')
                        this.lab_num.Text = "";
                    this.lab_num.Text += ((Button)sender).Text;
                    
                }
                    
                sing_op_flag = true;
                op_flag = true;
            }
        }


        private void input_Op(object sender, EventArgs e)
        {
            if (op_flag)
            {
                if (sing_op_flag == true)
                {
                    this.lab_show.Text += (this.lab_num.Text + ((Button)sender).Text);
                    list.Add(Double.Parse(this.lab_num.Text));
                }
                else
                    this.lab_show.Text += (((Button)sender).Text);
                
                list.Add(((Button)sender).Text);
                op_flag = false;
                sing_op_flag = false;
                num_flag = true;
                dot_flag = true;
                this.lab_num.Text = "0";
            }
        }

        private void input_Single_Op(object sender, EventArgs e)
        {

            if(sing_op_flag)
            {

                if (sender == btn_sqrt)
                {
                    list.Add(Math.Sqrt( Double.Parse(lab_num.Text)));
                    lab_show.Text += "Sqrt(" + lab_num.Text + ")";
                }
                if(sender==btn_rec)
                {
                    list.Add(1/Double.Parse(lab_num.Text));
                    lab_show.Text += "(1/" + lab_num.Text + ")";
                }
                if(sender==btn_neg)
                {
                    list.Add(-Math.Abs(Double.Parse(lab_num.Text)));
                    lab_show.Text += "(-" + lab_num.Text + ")";
                }

                input_Delete(btn_clsone, e);
                num_flag = false;
                sing_op_flag = false;
                op_flag = true;
            }
        }

        private void input_Delete(object sender, EventArgs e)
        {            
            if (sender == btn_del) {
                string str = (string)this.lab_num.Text;
                if (str.Length != 0)
                    this.lab_num.Text = str.Remove(str.Length-1);
                if (this.lab_num.Text.Length == 0)
                    this.lab_num.Text = "0";
                return;
            }
            if (sender == btn_clsone) 
                this.lab_num.Text = "0";
            if (sender == btn_clsall) 
            {
                this.lab_show.Text = "";
                this.lab_num.Text = "0";
                op_flag = sing_op_flag = false;
                num_flag = true;
                list.Clear();
                is_clear = false;
                
            }
            dot_flag = true;
        }

        private void result(object sender, EventArgs e)
        {
            if (!op_flag) return;
            list.Add(Double.Parse(lab_num.Text));
            if(!lab_num.Text.Equals("0"))
            lab_show.Text += lab_num.Text;
            try
            {
                string str = "";
                foreach(var val in list)
                {
                    str = str + val.ToString();
                }
                Queue<string> que = change(str);
                Double num1, num2;
                string ans;
                Stack<Double> st = new Stack<double>();

                foreach (var val in que)
                {
                    if (val[0] >= '0' && val[0] <= '9')
                    {
                        st.Push(Double.Parse(val));
                    }
                    else
                    {
                        Double num = 0;
                        num1 = st.Pop();
                        num2 = st.Pop();
                        char op = val[0];
                        if (op == '+') num = num1 + num2;
                        if (op == '-') num = num2 - num1;
                        if (op == '*') num = num1 * num2;
                        if (op == '%')
                        {
                            if (num1 == 0)
                            {
                                lab_show.Text = "输出不能为零";
                                return;
                            }
                            num = num2 % num1;
                        }
                        if (op == '/')
                        {
                            if (num1 == 0)
                            {
                                lab_show.Text = "输出不能为零";
                                return;
                            }
                            num = num2 / num1; 
                        }
                        st.Push(num);
                    }
                }
                ans = st.Peek().ToString();
                lab_show.Text += "=" + ans;
                
            }catch(Exception exce)
            {
                lab_show.Text = exce.Message;
            }finally
            {
                is_clear = true;
            }
        }

        /**
         * 中缀转后缀
         */
        private Queue<string> change(string str)
        {
            string temp = "";
            string str1 = "";
            Stack<string> s=new Stack<string>();
            Queue<string> que=new Queue<string>();
            
            int i;
            for (i = 0; i < str.Length; i++)
            {
                temp="";
                if (((i == 0 ) && (str[i] == '+' 
                    )) || (str[i] >= '0' && str[i] <= '9'))
                {
                    if (i >= str.Length)
                        break;
                    while (i < str.Length && (((i == 0 ) 
                        && (str[i] == '+')) || (str[i] >= '0' && str[i] <= '9' || str[i] == '.')))
                    {
                        if (str[i] != '+')
                            temp += str[i];
                        i++;
                    }
                    if (temp.Length>0)
                        que.Enqueue(temp);
                }
                if (i >= str.Length) break;
                if ( str[i] == '*' || str[i] == '/'||str[i]=='%')
                {
                    str1 = str[i].ToString();
                    s.Push(str1);
                }
                else if (str[i] == '+' || str[i] == '-')
                {
                    if (s.Count()==0)
                    {
                        str1 = str[i].ToString();
                        s.Push(str1);
                    }
                    else
                    {
                        do
                        {
                            str1 = s.Peek();
                            s.Pop();
                            que.Enqueue(str1);
                            
                        } while (s.Count()!=0);
                        str1 = str[i].ToString();
                        s.Push(str1);
                    }
                }
                str1="";
            }
            while (s.Count()>0)
            {
                str1 = s.Peek();
                s.Pop();
                que.Enqueue(str1);
            }

            //string num = "";
            //foreach (var val in que)
            //    num += val.ToString();
            //MessageBox.Show(num);
            return que;
        }
    }
}
