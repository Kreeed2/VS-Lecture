namespace ClientApplication
{
    partial class MainWindow
    {
        /// <summary>
        /// Erforderliche Designervariable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Verwendete Ressourcen bereinigen.
        /// </summary>
        /// <param name="disposing">True, wenn verwaltete Ressourcen gelöscht werden sollen; andernfalls False.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Vom Windows Form-Designer generierter Code

        /// <summary>
        /// Erforderliche Methode für die Designerunterstützung.
        /// Der Inhalt der Methode darf nicht mit dem Code-Editor geändert werden.
        /// </summary>
        private void InitializeComponent()
        {
            this.lbl_IP = new System.Windows.Forms.Label();
            this.lbl_file = new System.Windows.Forms.Label();
            this.txt_IP = new System.Windows.Forms.TextBox();
            this.txt_file = new System.Windows.Forms.TextBox();
            this.txt_display = new System.Windows.Forms.RichTextBox();
            this.btn_request = new System.Windows.Forms.Button();
            this.SuspendLayout();
            // 
            // lbl_IP
            // 
            this.lbl_IP.AutoSize = true;
            this.lbl_IP.Location = new System.Drawing.Point(14, 8);
            this.lbl_IP.Name = "lbl_IP";
            this.lbl_IP.Size = new System.Drawing.Size(51, 13);
            this.lbl_IP.TabIndex = 0;
            this.lbl_IP.Text = "Server IP";
            // 
            // lbl_file
            // 
            this.lbl_file.AutoSize = true;
            this.lbl_file.Location = new System.Drawing.Point(13, 33);
            this.lbl_file.Name = "lbl_file";
            this.lbl_file.Size = new System.Drawing.Size(54, 13);
            this.lbl_file.TabIndex = 0;
            this.lbl_file.Text = "File Name";
            // 
            // txt_IP
            // 
            this.txt_IP.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.txt_IP.Location = new System.Drawing.Point(99, 5);
            this.txt_IP.Name = "txt_IP";
            this.txt_IP.Size = new System.Drawing.Size(173, 20);
            this.txt_IP.TabIndex = 1;
            this.txt_IP.Text = "127.0.0.1";
            // 
            // txt_file
            // 
            this.txt_file.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.txt_file.Location = new System.Drawing.Point(99, 30);
            this.txt_file.Name = "txt_file";
            this.txt_file.Size = new System.Drawing.Size(173, 20);
            this.txt_file.TabIndex = 2;
            // 
            // txt_display
            // 
            this.txt_display.Anchor = ((System.Windows.Forms.AnchorStyles)((((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Bottom) 
            | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.txt_display.Location = new System.Drawing.Point(12, 85);
            this.txt_display.Name = "txt_display";
            this.txt_display.ReadOnly = true;
            this.txt_display.Size = new System.Drawing.Size(260, 164);
            this.txt_display.TabIndex = 0;
            this.txt_display.Text = "";
            // 
            // btn_request
            // 
            this.btn_request.Anchor = ((System.Windows.Forms.AnchorStyles)(((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Left) 
            | System.Windows.Forms.AnchorStyles.Right)));
            this.btn_request.Location = new System.Drawing.Point(99, 56);
            this.btn_request.Name = "btn_request";
            this.btn_request.Size = new System.Drawing.Size(173, 23);
            this.btn_request.TabIndex = 3;
            this.btn_request.Text = "Show File";
            this.btn_request.UseVisualStyleBackColor = true;
            this.btn_request.Click += new System.EventHandler(this.Btn_request_Click);
            // 
            // MainWindow
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(284, 261);
            this.Controls.Add(this.btn_request);
            this.Controls.Add(this.txt_display);
            this.Controls.Add(this.txt_file);
            this.Controls.Add(this.txt_IP);
            this.Controls.Add(this.lbl_file);
            this.Controls.Add(this.lbl_IP);
            this.Name = "MainWindow";
            this.ShowIcon = false;
            this.Text = "Client";
            this.ResumeLayout(false);
            this.PerformLayout();

        }

        #endregion

        private System.Windows.Forms.Label lbl_IP;
        private System.Windows.Forms.Label lbl_file;
        private System.Windows.Forms.TextBox txt_IP;
        private System.Windows.Forms.TextBox txt_file;
        private System.Windows.Forms.RichTextBox txt_display;
        private System.Windows.Forms.Button btn_request;
    }
}

