using System.Drawing;
using System.Windows.Forms;
using System;

namespace InterfazRest
{
    partial class RegisterForm
    {
        /// <summary>
        /// Variable del diseñador necesaria.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén usando.
        /// </summary>
        /// <param name="disposing">true si los recursos administrados se deben desechar; false en caso contrario.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
                
            }
            base.Dispose(disposing);
        }
        

        #region Código generado por el Diseñador de Windows Forms

        /// <summary>
        /// Método necesario para admitir el Diseñador. No se puede modificar
        /// el contenido de este método con el editor de código.
        /// </summary>
        private void InitializeComponent()
        {
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(RegisterForm));
            this.lblUsername = new System.Windows.Forms.Label();
            this.lblEmail = new System.Windows.Forms.Label();
            this.lblPass = new System.Windows.Forms.Label();
            this.txtUsername = new System.Windows.Forms.TextBox();
            this.txtEmail = new System.Windows.Forms.TextBox();
            this.txtPassword = new System.Windows.Forms.TextBox();
            this.lblRole = new System.Windows.Forms.Label();
            this.panelRegister = new System.Windows.Forms.Panel();
            this.panel1 = new System.Windows.Forms.Panel();
            this.btnExit = new System.Windows.Forms.Button();
            this.btnSubmit = new FontAwesome.Sharp.IconButton();
            this.iconButtonPass = new FontAwesome.Sharp.IconButton();
            this.iconButtonEmail = new FontAwesome.Sharp.IconButton();
            this.iconButtonUser = new FontAwesome.Sharp.IconButton();
            this.radioButtonUser = new System.Windows.Forms.RadioButton();
            this.radioButtonAdmin = new System.Windows.Forms.RadioButton();
            this.panelBase = new System.Windows.Forms.Panel();
            this.panelRegister.SuspendLayout();
            this.panel1.SuspendLayout();
            this.panelBase.SuspendLayout();
            this.SuspendLayout();
            // 
            // lblUsername
            // 
            this.lblUsername.AutoSize = true;
            this.lblUsername.BackColor = System.Drawing.Color.Transparent;
            this.lblUsername.Font = new System.Drawing.Font("Arial", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblUsername.ForeColor = System.Drawing.Color.Transparent;
            this.lblUsername.Location = new System.Drawing.Point(21, 82);
            this.lblUsername.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblUsername.Name = "lblUsername";
            this.lblUsername.Size = new System.Drawing.Size(76, 17);
            this.lblUsername.TabIndex = 2;
            this.lblUsername.Text = "Username";
            // 
            // lblEmail
            // 
            this.lblEmail.AutoSize = true;
            this.lblEmail.BackColor = System.Drawing.Color.Transparent;
            this.lblEmail.Font = new System.Drawing.Font("Arial", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblEmail.ForeColor = System.Drawing.Color.Transparent;
            this.lblEmail.Location = new System.Drawing.Point(21, 135);
            this.lblEmail.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblEmail.Name = "lblEmail";
            this.lblEmail.Size = new System.Drawing.Size(45, 17);
            this.lblEmail.TabIndex = 3;
            this.lblEmail.Text = "Email";
            // 
            // lblPass
            // 
            this.lblPass.AutoSize = true;
            this.lblPass.BackColor = System.Drawing.Color.Transparent;
            this.lblPass.Font = new System.Drawing.Font("Arial", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblPass.ForeColor = System.Drawing.Color.Transparent;
            this.lblPass.Location = new System.Drawing.Point(21, 189);
            this.lblPass.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblPass.Name = "lblPass";
            this.lblPass.Size = new System.Drawing.Size(74, 17);
            this.lblPass.TabIndex = 4;
            this.lblPass.Text = "Password";
            // 
            // txtUsername
            // 
            this.txtUsername.BackColor = System.Drawing.SystemColors.Menu;
            this.txtUsername.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtUsername.Font = new System.Drawing.Font("Arial", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtUsername.ForeColor = System.Drawing.Color.Gray;
            this.txtUsername.Location = new System.Drawing.Point(132, 78);
            this.txtUsername.Margin = new System.Windows.Forms.Padding(4);
            this.txtUsername.MaxLength = 20;
            this.txtUsername.Multiline = true;
            this.txtUsername.Name = "txtUsername";
            this.txtUsername.Size = new System.Drawing.Size(238, 25);
            this.txtUsername.TabIndex = 5;
            // 
            // txtEmail
            // 
            this.txtEmail.BackColor = System.Drawing.SystemColors.Menu;
            this.txtEmail.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtEmail.Font = new System.Drawing.Font("Arial", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtEmail.ForeColor = System.Drawing.Color.Gray;
            this.txtEmail.Location = new System.Drawing.Point(132, 131);
            this.txtEmail.Margin = new System.Windows.Forms.Padding(4);
            this.txtEmail.MaxLength = 50;
            this.txtEmail.Multiline = true;
            this.txtEmail.Name = "txtEmail";
            this.txtEmail.Size = new System.Drawing.Size(238, 25);
            this.txtEmail.TabIndex = 6;
            // 
            // txtPassword
            // 
            this.txtPassword.BackColor = System.Drawing.SystemColors.Menu;
            this.txtPassword.BorderStyle = System.Windows.Forms.BorderStyle.None;
            this.txtPassword.Font = new System.Drawing.Font("Arial", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtPassword.ForeColor = System.Drawing.Color.Gray;
            this.txtPassword.Location = new System.Drawing.Point(132, 189);
            this.txtPassword.Margin = new System.Windows.Forms.Padding(4);
            this.txtPassword.MaxLength = 50;
            this.txtPassword.Multiline = true;
            this.txtPassword.Name = "txtPassword";
            this.txtPassword.PasswordChar = '*';
            this.txtPassword.Size = new System.Drawing.Size(238, 25);
            this.txtPassword.TabIndex = 7;
            // 
            // lblRole
            // 
            this.lblRole.AutoSize = true;
            this.lblRole.BackColor = System.Drawing.Color.Transparent;
            this.lblRole.Font = new System.Drawing.Font("Arial", 11.25F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblRole.ForeColor = System.Drawing.Color.Transparent;
            this.lblRole.Location = new System.Drawing.Point(21, 243);
            this.lblRole.Margin = new System.Windows.Forms.Padding(4, 0, 4, 0);
            this.lblRole.Name = "lblRole";
            this.lblRole.Size = new System.Drawing.Size(46, 17);
            this.lblRole.TabIndex = 10;
            this.lblRole.Text = "Roles";
            // 
            // panelRegister
            // 
            this.panelRegister.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.panelRegister.Controls.Add(this.panel1);
            this.panelRegister.Controls.Add(this.btnSubmit);
            this.panelRegister.Controls.Add(this.iconButtonPass);
            this.panelRegister.Controls.Add(this.iconButtonEmail);
            this.panelRegister.Controls.Add(this.iconButtonUser);
            this.panelRegister.Controls.Add(this.radioButtonUser);
            this.panelRegister.Controls.Add(this.radioButtonAdmin);
            this.panelRegister.Controls.Add(this.txtEmail);
            this.panelRegister.Controls.Add(this.lblRole);
            this.panelRegister.Controls.Add(this.lblUsername);
            this.panelRegister.Controls.Add(this.lblEmail);
            this.panelRegister.Controls.Add(this.lblPass);
            this.panelRegister.Controls.Add(this.txtUsername);
            this.panelRegister.Controls.Add(this.txtPassword);
            this.panelRegister.Location = new System.Drawing.Point(492, 181);
            this.panelRegister.Name = "panelRegister";
            this.panelRegister.Size = new System.Drawing.Size(399, 380);
            this.panelRegister.TabIndex = 13;
            // 
            // panel1
            // 
            this.panel1.Controls.Add(this.btnExit);
            this.panel1.Dock = System.Windows.Forms.DockStyle.Top;
            this.panel1.Location = new System.Drawing.Point(0, 0);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(399, 27);
            this.panel1.TabIndex = 19;
            // 
            // btnExit
            // 
            this.btnExit.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
       //     this.btnExit.BackgroundImage = global::InterfazRest.Properties.Resources.close;
            this.btnExit.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnExit.FlatAppearance.BorderSize = 0;
            this.btnExit.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnExit.ForeColor = System.Drawing.Color.Bisque;
            this.btnExit.Location = new System.Drawing.Point(371, 0);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(25, 25);
            this.btnExit.TabIndex = 4;
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // btnSubmit
            // 
            this.btnSubmit.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.btnSubmit.FlatAppearance.BorderSize = 0;
            this.btnSubmit.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSubmit.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSubmit.ForeColor = System.Drawing.SystemColors.Menu;
            this.btnSubmit.IconChar = FontAwesome.Sharp.IconChar.CircleCheck;
            this.btnSubmit.IconColor = System.Drawing.Color.White;
            this.btnSubmit.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.btnSubmit.IconSize = 45;
            this.btnSubmit.Location = new System.Drawing.Point(213, 299);
            this.btnSubmit.Name = "btnSubmit";
            this.btnSubmit.Padding = new System.Windows.Forms.Padding(10, 0, 10, 0);
            this.btnSubmit.Size = new System.Drawing.Size(157, 50);
            this.btnSubmit.TabIndex = 18;
            this.btnSubmit.Text = "Registrar";
            this.btnSubmit.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.btnSubmit.UseVisualStyleBackColor = false;
            this.btnSubmit.Click += new System.EventHandler(this.btnSubmit_Click);
            // 
            // iconButtonPass
            // 
            this.iconButtonPass.BackColor = System.Drawing.SystemColors.Window;
            this.iconButtonPass.FlatAppearance.BorderSize = 0;
            this.iconButtonPass.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonPass.IconChar = FontAwesome.Sharp.IconChar.Eye;
            this.iconButtonPass.IconColor = System.Drawing.Color.Gainsboro;
            this.iconButtonPass.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonPass.IconSize = 25;
            this.iconButtonPass.Location = new System.Drawing.Point(335, 189);
            this.iconButtonPass.Name = "iconButtonPass";
            this.iconButtonPass.Size = new System.Drawing.Size(35, 25);
            this.iconButtonPass.TabIndex = 17;
            this.iconButtonPass.UseVisualStyleBackColor = false;
            this.iconButtonPass.MouseDown += new System.Windows.Forms.MouseEventHandler(this.iconButtonPass_MouseDown);
            this.iconButtonPass.MouseUp += new System.Windows.Forms.MouseEventHandler(this.iconButtonPass_MouseUp);
            // 
            // iconButtonEmail
            // 
            this.iconButtonEmail.BackColor = System.Drawing.SystemColors.Window;
            this.iconButtonEmail.FlatAppearance.BorderSize = 0;
            this.iconButtonEmail.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonEmail.IconChar = FontAwesome.Sharp.IconChar.Envelope;
            this.iconButtonEmail.IconColor = System.Drawing.Color.Gainsboro;
            this.iconButtonEmail.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonEmail.IconSize = 25;
            this.iconButtonEmail.Location = new System.Drawing.Point(335, 131);
            this.iconButtonEmail.Name = "iconButtonEmail";
            this.iconButtonEmail.Size = new System.Drawing.Size(35, 25);
            this.iconButtonEmail.TabIndex = 16;
            this.iconButtonEmail.UseVisualStyleBackColor = false;
            // 
            // iconButtonUser
            // 
            this.iconButtonUser.BackColor = System.Drawing.SystemColors.Window;
            this.iconButtonUser.FlatAppearance.BorderSize = 0;
            this.iconButtonUser.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonUser.IconChar = FontAwesome.Sharp.IconChar.User;
            this.iconButtonUser.IconColor = System.Drawing.Color.Gainsboro;
            this.iconButtonUser.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonUser.IconSize = 25;
            this.iconButtonUser.Location = new System.Drawing.Point(335, 78);
            this.iconButtonUser.Name = "iconButtonUser";
            this.iconButtonUser.Size = new System.Drawing.Size(35, 25);
            this.iconButtonUser.TabIndex = 15;
            this.iconButtonUser.UseVisualStyleBackColor = false;
            // 
            // radioButtonUser
            // 
            this.radioButtonUser.AutoSize = true;
            this.radioButtonUser.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radioButtonUser.ForeColor = System.Drawing.Color.Transparent;
            this.radioButtonUser.Location = new System.Drawing.Point(132, 243);
            this.radioButtonUser.Name = "radioButtonUser";
            this.radioButtonUser.Size = new System.Drawing.Size(61, 20);
            this.radioButtonUser.TabIndex = 14;
            this.radioButtonUser.TabStop = true;
            this.radioButtonUser.Text = "USER";
            this.radioButtonUser.UseVisualStyleBackColor = true;
            // 
            // radioButtonAdmin
            // 
            this.radioButtonAdmin.AutoSize = true;
            this.radioButtonAdmin.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.radioButtonAdmin.ForeColor = System.Drawing.Color.Transparent;
            this.radioButtonAdmin.Location = new System.Drawing.Point(223, 243);
            this.radioButtonAdmin.Name = "radioButtonAdmin";
            this.radioButtonAdmin.Size = new System.Drawing.Size(66, 20);
            this.radioButtonAdmin.TabIndex = 13;
            this.radioButtonAdmin.TabStop = true;
            this.radioButtonAdmin.Text = "ADMIN";
            this.radioButtonAdmin.UseVisualStyleBackColor = true;
            // 
            // panelBase
            // 
            this.panelBase.BackgroundImage = global::InterfazRest.Properties.Resources.silueta_transparente;
            this.panelBase.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.panelBase.Controls.Add(this.panelRegister);
            this.panelBase.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelBase.Location = new System.Drawing.Point(0, 0);
            this.panelBase.Name = "panelBase";
            this.panelBase.Size = new System.Drawing.Size(987, 761);
            this.panelBase.TabIndex = 14;
            // 
            // RegisterForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(9F, 18F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(224)))), ((int)(((byte)(224)))), ((int)(((byte)(224)))));
            this.ClientSize = new System.Drawing.Size(987, 761);
            this.Controls.Add(this.panelBase);
            this.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.Icon = ((System.Drawing.Icon)(resources.GetObject("$this.Icon")));
            this.Margin = new System.Windows.Forms.Padding(4);
            this.Name = "RegisterForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Registro de Atleta";
            this.panelRegister.ResumeLayout(false);
            this.panelRegister.PerformLayout();
            this.panel1.ResumeLayout(false);
            this.panelBase.ResumeLayout(false);
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Label lblUsername;
        private System.Windows.Forms.Label lblEmail;
        private System.Windows.Forms.Label lblPass;
        private System.Windows.Forms.TextBox txtUsername;
        private System.Windows.Forms.TextBox txtEmail;
        private System.Windows.Forms.TextBox txtPassword;
        private Label lblRole;
        private Panel panelRegister;
        private Panel panelBase;
        private RadioButton radioButtonUser;
        private RadioButton radioButtonAdmin;
        private FontAwesome.Sharp.IconButton iconButtonUser;
        private FontAwesome.Sharp.IconButton iconButtonPass;
        private FontAwesome.Sharp.IconButton iconButtonEmail;
        private FontAwesome.Sharp.IconButton btnSubmit;
        private Panel panel1;
        private Button btnExit;
    }
}

