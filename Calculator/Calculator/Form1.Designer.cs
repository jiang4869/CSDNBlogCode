namespace Calculator
{
    partial class Form1
    {
        /// <summary>
        /// 必需的设计器变量。
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// 清理所有正在使用的资源。
        /// </summary>
        /// <param name="disposing">如果应释放托管资源，为 true；否则为 false。</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows 窗体设计器生成的代码

        /// <summary>
        /// 设计器支持所需的方法 - 不要修改
        /// 使用代码编辑器修改此方法的内容。
        /// </summary>
        private void InitializeComponent()
        {
            this.btn_1 = new System.Windows.Forms.Button();
            this.btn_7 = new System.Windows.Forms.Button();
            this.btn_6 = new System.Windows.Forms.Button();
            this.btn_5 = new System.Windows.Forms.Button();
            this.btn_4 = new System.Windows.Forms.Button();
            this.btn_3 = new System.Windows.Forms.Button();
            this.btn_2 = new System.Windows.Forms.Button();
            this.btn_9 = new System.Windows.Forms.Button();
            this.btn_8 = new System.Windows.Forms.Button();
            this.btn_clsall = new System.Windows.Forms.Button();
            this.btn_clsone = new System.Windows.Forms.Button();
            this.btn_del = new System.Windows.Forms.Button();
            this.btn_sqrt = new System.Windows.Forms.Button();
            this.btn_mod = new System.Windows.Forms.Button();
            this.btn_neg = new System.Windows.Forms.Button();
            this.btn_div = new System.Windows.Forms.Button();
            this.btn_mult = new System.Windows.Forms.Button();
            this.btn_sub = new System.Windows.Forms.Button();
            this.btn_dot = new System.Windows.Forms.Button();
            this.btn_plus = new System.Windows.Forms.Button();
            this.btn_rec = new System.Windows.Forms.Button();
            this.btn_0 = new System.Windows.Forms.Button();
            this.btn_equal = new System.Windows.Forms.Button();
            this.process1 = new System.Diagnostics.Process();
            this.lab_show = new System.Windows.Forms.Label();
            this.lab_num = new System.Windows.Forms.Label();
            this.SuspendLayout();
            // 
            // btn_1
            // 
            this.btn_1.Location = new System.Drawing.Point(12, 297);
            this.btn_1.Name = "btn_1";
            this.btn_1.Size = new System.Drawing.Size(50, 50);
            this.btn_1.TabIndex = 1;
            this.btn_1.Text = "1";
            this.btn_1.UseVisualStyleBackColor = true;
            this.btn_1.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_7
            // 
            this.btn_7.Location = new System.Drawing.Point(12, 185);
            this.btn_7.Name = "btn_7";
            this.btn_7.Size = new System.Drawing.Size(50, 50);
            this.btn_7.TabIndex = 2;
            this.btn_7.Text = "7";
            this.btn_7.UseVisualStyleBackColor = true;
            this.btn_7.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_6
            // 
            this.btn_6.Location = new System.Drawing.Point(124, 241);
            this.btn_6.Name = "btn_6";
            this.btn_6.Size = new System.Drawing.Size(50, 50);
            this.btn_6.TabIndex = 3;
            this.btn_6.Text = "6";
            this.btn_6.UseVisualStyleBackColor = true;
            this.btn_6.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_5
            // 
            this.btn_5.Location = new System.Drawing.Point(68, 241);
            this.btn_5.Name = "btn_5";
            this.btn_5.Size = new System.Drawing.Size(50, 50);
            this.btn_5.TabIndex = 4;
            this.btn_5.Text = "5";
            this.btn_5.UseVisualStyleBackColor = true;
            this.btn_5.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_4
            // 
            this.btn_4.Location = new System.Drawing.Point(12, 241);
            this.btn_4.Name = "btn_4";
            this.btn_4.Size = new System.Drawing.Size(50, 50);
            this.btn_4.TabIndex = 5;
            this.btn_4.Text = "4";
            this.btn_4.UseVisualStyleBackColor = true;
            this.btn_4.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_3
            // 
            this.btn_3.Location = new System.Drawing.Point(124, 297);
            this.btn_3.Name = "btn_3";
            this.btn_3.Size = new System.Drawing.Size(50, 50);
            this.btn_3.TabIndex = 6;
            this.btn_3.Text = "3";
            this.btn_3.UseVisualStyleBackColor = true;
            this.btn_3.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_2
            // 
            this.btn_2.Location = new System.Drawing.Point(68, 297);
            this.btn_2.Name = "btn_2";
            this.btn_2.Size = new System.Drawing.Size(50, 50);
            this.btn_2.TabIndex = 7;
            this.btn_2.Text = "2";
            this.btn_2.UseVisualStyleBackColor = true;
            this.btn_2.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_9
            // 
            this.btn_9.Location = new System.Drawing.Point(124, 185);
            this.btn_9.Name = "btn_9";
            this.btn_9.Size = new System.Drawing.Size(50, 50);
            this.btn_9.TabIndex = 8;
            this.btn_9.Text = "9";
            this.btn_9.UseVisualStyleBackColor = true;
            this.btn_9.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_8
            // 
            this.btn_8.Location = new System.Drawing.Point(68, 185);
            this.btn_8.Name = "btn_8";
            this.btn_8.Size = new System.Drawing.Size(50, 50);
            this.btn_8.TabIndex = 9;
            this.btn_8.Text = "8";
            this.btn_8.UseVisualStyleBackColor = true;
            this.btn_8.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_clsall
            // 
            this.btn_clsall.Location = new System.Drawing.Point(124, 129);
            this.btn_clsall.Name = "btn_clsall";
            this.btn_clsall.Size = new System.Drawing.Size(50, 50);
            this.btn_clsall.TabIndex = 11;
            this.btn_clsall.Text = "C";
            this.btn_clsall.UseVisualStyleBackColor = true;
            this.btn_clsall.Click += new System.EventHandler(this.input_Delete);
            // 
            // btn_clsone
            // 
            this.btn_clsone.Location = new System.Drawing.Point(68, 129);
            this.btn_clsone.Name = "btn_clsone";
            this.btn_clsone.Size = new System.Drawing.Size(50, 50);
            this.btn_clsone.TabIndex = 14;
            this.btn_clsone.Text = "CE";
            this.btn_clsone.UseVisualStyleBackColor = true;
            this.btn_clsone.Click += new System.EventHandler(this.input_Delete);
            // 
            // btn_del
            // 
            this.btn_del.Location = new System.Drawing.Point(12, 129);
            this.btn_del.Name = "btn_del";
            this.btn_del.Size = new System.Drawing.Size(50, 50);
            this.btn_del.TabIndex = 15;
            this.btn_del.Text = "<-";
            this.btn_del.UseVisualStyleBackColor = true;
            this.btn_del.Click += new System.EventHandler(this.input_Delete);
            // 
            // btn_sqrt
            // 
            this.btn_sqrt.Location = new System.Drawing.Point(236, 129);
            this.btn_sqrt.Name = "btn_sqrt";
            this.btn_sqrt.Size = new System.Drawing.Size(50, 50);
            this.btn_sqrt.TabIndex = 17;
            this.btn_sqrt.Text = "√";
            this.btn_sqrt.UseVisualStyleBackColor = true;
            this.btn_sqrt.Click += new System.EventHandler(this.input_Single_Op);
            // 
            // btn_mod
            // 
            this.btn_mod.Location = new System.Drawing.Point(236, 182);
            this.btn_mod.Name = "btn_mod";
            this.btn_mod.Size = new System.Drawing.Size(50, 50);
            this.btn_mod.TabIndex = 18;
            this.btn_mod.Text = "%";
            this.btn_mod.UseVisualStyleBackColor = true;
            this.btn_mod.Click += new System.EventHandler(this.input_Op);
            // 
            // btn_neg
            // 
            this.btn_neg.Location = new System.Drawing.Point(180, 129);
            this.btn_neg.Name = "btn_neg";
            this.btn_neg.Size = new System.Drawing.Size(50, 50);
            this.btn_neg.TabIndex = 19;
            this.btn_neg.Text = "±";
            this.btn_neg.UseVisualStyleBackColor = true;
            this.btn_neg.Click += new System.EventHandler(this.input_Single_Op);
            // 
            // btn_div
            // 
            this.btn_div.Location = new System.Drawing.Point(180, 182);
            this.btn_div.Name = "btn_div";
            this.btn_div.Size = new System.Drawing.Size(50, 50);
            this.btn_div.TabIndex = 20;
            this.btn_div.Text = "/";
            this.btn_div.UseVisualStyleBackColor = true;
            this.btn_div.Click += new System.EventHandler(this.input_Op);
            // 
            // btn_mult
            // 
            this.btn_mult.Location = new System.Drawing.Point(180, 241);
            this.btn_mult.Name = "btn_mult";
            this.btn_mult.Size = new System.Drawing.Size(50, 50);
            this.btn_mult.TabIndex = 21;
            this.btn_mult.Text = "*";
            this.btn_mult.UseVisualStyleBackColor = true;
            this.btn_mult.Click += new System.EventHandler(this.input_Op);
            // 
            // btn_sub
            // 
            this.btn_sub.Location = new System.Drawing.Point(180, 297);
            this.btn_sub.Name = "btn_sub";
            this.btn_sub.Size = new System.Drawing.Size(50, 50);
            this.btn_sub.TabIndex = 22;
            this.btn_sub.Text = "-";
            this.btn_sub.UseVisualStyleBackColor = true;
            this.btn_sub.Click += new System.EventHandler(this.input_Op);
            // 
            // btn_dot
            // 
            this.btn_dot.Location = new System.Drawing.Point(124, 353);
            this.btn_dot.Name = "btn_dot";
            this.btn_dot.Size = new System.Drawing.Size(50, 50);
            this.btn_dot.TabIndex = 24;
            this.btn_dot.Text = ".";
            this.btn_dot.UseVisualStyleBackColor = true;
            this.btn_dot.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_plus
            // 
            this.btn_plus.Location = new System.Drawing.Point(180, 353);
            this.btn_plus.Name = "btn_plus";
            this.btn_plus.Size = new System.Drawing.Size(50, 50);
            this.btn_plus.TabIndex = 25;
            this.btn_plus.Text = "+";
            this.btn_plus.UseVisualStyleBackColor = true;
            this.btn_plus.Click += new System.EventHandler(this.input_Op);
            // 
            // btn_rec
            // 
            this.btn_rec.Location = new System.Drawing.Point(236, 241);
            this.btn_rec.Name = "btn_rec";
            this.btn_rec.Size = new System.Drawing.Size(50, 50);
            this.btn_rec.TabIndex = 26;
            this.btn_rec.Text = "1/x";
            this.btn_rec.UseVisualStyleBackColor = true;
            this.btn_rec.Click += new System.EventHandler(this.input_Single_Op);
            // 
            // btn_0
            // 
            this.btn_0.Location = new System.Drawing.Point(12, 353);
            this.btn_0.Name = "btn_0";
            this.btn_0.Size = new System.Drawing.Size(106, 50);
            this.btn_0.TabIndex = 27;
            this.btn_0.Text = "0";
            this.btn_0.UseVisualStyleBackColor = true;
            this.btn_0.Click += new System.EventHandler(this.input_Number);
            // 
            // btn_equal
            // 
            this.btn_equal.Location = new System.Drawing.Point(236, 297);
            this.btn_equal.Name = "btn_equal";
            this.btn_equal.Size = new System.Drawing.Size(50, 106);
            this.btn_equal.TabIndex = 28;
            this.btn_equal.Text = "=";
            this.btn_equal.UseVisualStyleBackColor = true;
            this.btn_equal.Click += new System.EventHandler(this.result);
            // 
            // process1
            // 
            this.process1.StartInfo.Domain = "";
            this.process1.StartInfo.LoadUserProfile = false;
            this.process1.StartInfo.Password = null;
            this.process1.StartInfo.StandardErrorEncoding = null;
            this.process1.StartInfo.StandardOutputEncoding = null;
            this.process1.StartInfo.UserName = "";
            this.process1.SynchronizingObject = this;
            // 
            // lab_show
            // 
            this.lab_show.Font = new System.Drawing.Font("宋体", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.lab_show.Location = new System.Drawing.Point(12, 26);
            this.lab_show.Name = "lab_show";
            this.lab_show.Size = new System.Drawing.Size(274, 34);
            this.lab_show.TabIndex = 29;
            this.lab_show.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;

            // 
            // lab_num
            // 
            this.lab_num.Font = new System.Drawing.Font("宋体", 15F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(134)));
            this.lab_num.Location = new System.Drawing.Point(12, 60);
            this.lab_num.Name = "lab_num";
            this.lab_num.Size = new System.Drawing.Size(274, 34);
            this.lab_num.TabIndex = 30;
            this.lab_num.Text = "0";
            this.lab_num.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            // 
            // Form1
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 12F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(309, 429);
            this.Controls.Add(this.lab_num);
            this.Controls.Add(this.lab_show);
            this.Controls.Add(this.btn_equal);
            this.Controls.Add(this.btn_0);
            this.Controls.Add(this.btn_rec);
            this.Controls.Add(this.btn_plus);
            this.Controls.Add(this.btn_dot);
            this.Controls.Add(this.btn_sub);
            this.Controls.Add(this.btn_mult);
            this.Controls.Add(this.btn_div);
            this.Controls.Add(this.btn_neg);
            this.Controls.Add(this.btn_mod);
            this.Controls.Add(this.btn_sqrt);
            this.Controls.Add(this.btn_del);
            this.Controls.Add(this.btn_clsone);
            this.Controls.Add(this.btn_clsall);
            this.Controls.Add(this.btn_8);
            this.Controls.Add(this.btn_9);
            this.Controls.Add(this.btn_2);
            this.Controls.Add(this.btn_3);
            this.Controls.Add(this.btn_4);
            this.Controls.Add(this.btn_5);
            this.Controls.Add(this.btn_6);
            this.Controls.Add(this.btn_7);
            this.Controls.Add(this.btn_1);
            this.Name = "Form1";
            this.Text = "Form1";
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Button btn_1;
        private System.Windows.Forms.Button btn_7;
        private System.Windows.Forms.Button btn_6;
        private System.Windows.Forms.Button btn_5;
        private System.Windows.Forms.Button btn_4;
        private System.Windows.Forms.Button btn_3;
        private System.Windows.Forms.Button btn_2;
        private System.Windows.Forms.Button btn_9;
        private System.Windows.Forms.Button btn_8;
        private System.Windows.Forms.Button btn_clsall;
        private System.Windows.Forms.Button btn_clsone;
        private System.Windows.Forms.Button btn_del;
        private System.Windows.Forms.Button btn_sqrt;
        private System.Windows.Forms.Button btn_mod;
        private System.Windows.Forms.Button btn_neg;
        private System.Windows.Forms.Button btn_div;
        private System.Windows.Forms.Button btn_mult;
        private System.Windows.Forms.Button btn_sub;
        private System.Windows.Forms.Button btn_dot;
        private System.Windows.Forms.Button btn_plus;
        private System.Windows.Forms.Button btn_rec;
        private System.Windows.Forms.Button btn_0;
        private System.Windows.Forms.Button btn_equal;
        private System.Diagnostics.Process process1;
        private System.Windows.Forms.Label lab_show;
        private System.Windows.Forms.Label lab_num;
    }



}

