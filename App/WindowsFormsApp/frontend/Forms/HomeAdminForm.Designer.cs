namespace InterfazRest
{
    partial class HomeAdminForm
    {
        /// <summary>
        /// Variable del diseñador requerida.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Limpiar los recursos que se estén utilizando.
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
            this.panelTitleBar = new System.Windows.Forms.Panel();
            this.iconButton1 = new FontAwesome.Sharp.IconButton();
            this.iconButton2 = new FontAwesome.Sharp.IconButton();
            this.iconButton3 = new FontAwesome.Sharp.IconButton();
            this.iconCurrentChildForm = new FontAwesome.Sharp.IconPictureBox();
            this.lblUsername = new System.Windows.Forms.Label();
            this.lblName = new System.Windows.Forms.Label();
            this.btnMax = new System.Windows.Forms.Button();
            this.btnMinus = new System.Windows.Forms.Button();
            this.btnExit = new System.Windows.Forms.Button();
            this.panelMenu = new System.Windows.Forms.Panel();
            this.iconButtonProfile = new FontAwesome.Sharp.IconButton();
            this.iconButtonShop = new FontAwesome.Sharp.IconButton();
            this.iconButtonAdmin = new FontAwesome.Sharp.IconButton();
            this.iconButtonContact = new FontAwesome.Sharp.IconButton();
            this.iconButtonCalendar = new FontAwesome.Sharp.IconButton();
            this.iconButtonTrainers = new FontAwesome.Sharp.IconButton();
            this.iconButtonEvent = new FontAwesome.Sharp.IconButton();
            this.iconButtonRegister = new FontAwesome.Sharp.IconButton();
            this.iconButtonAtleta = new FontAwesome.Sharp.IconButton();
            this.iconButtonLogout = new FontAwesome.Sharp.IconButton();
            this.panelFoto = new System.Windows.Forms.Panel();
            this.pictureBox1 = new System.Windows.Forms.PictureBox();
            this.panelDesktop = new System.Windows.Forms.Panel();
            this.panelTitleBar.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.iconCurrentChildForm)).BeginInit();
            this.panelMenu.SuspendLayout();
            this.panelFoto.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).BeginInit();
            this.SuspendLayout();
            // 
            // panelTitleBar
            // 
            this.panelTitleBar.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.panelTitleBar.Controls.Add(this.iconButton1);
            this.panelTitleBar.Controls.Add(this.iconButton2);
            this.panelTitleBar.Controls.Add(this.iconButton3);
            this.panelTitleBar.Controls.Add(this.iconCurrentChildForm);
            this.panelTitleBar.Controls.Add(this.lblUsername);
            this.panelTitleBar.Controls.Add(this.lblName);
            this.panelTitleBar.Controls.Add(this.btnMax);
            this.panelTitleBar.Controls.Add(this.btnMinus);
            this.panelTitleBar.Controls.Add(this.btnExit);
            this.panelTitleBar.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelTitleBar.Location = new System.Drawing.Point(197, 0);
            this.panelTitleBar.Name = "panelTitleBar";
            this.panelTitleBar.Size = new System.Drawing.Size(987, 100);
            this.panelTitleBar.TabIndex = 3;
            // 
            // iconButton1
            // 
            this.iconButton1.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.iconButton1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.iconButton1.FlatAppearance.BorderSize = 0;
            this.iconButton1.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButton1.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButton1.ForeColor = System.Drawing.Color.White;
            this.iconButton1.IconChar = FontAwesome.Sharp.IconChar.WindowMinimize;
            this.iconButton1.IconColor = System.Drawing.Color.White;
            this.iconButton1.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButton1.IconSize = 20;
            this.iconButton1.Location = new System.Drawing.Point(895, 4);
            this.iconButton1.Name = "iconButton1";
            this.iconButton1.Size = new System.Drawing.Size(30, 30);
            this.iconButton1.TabIndex = 13;
            this.iconButton1.UseVisualStyleBackColor = false;
            this.iconButton1.Click += new System.EventHandler(this.btnMinus_Click);
            // 
            // iconButton2
            // 
            this.iconButton2.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.iconButton2.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.iconButton2.FlatAppearance.BorderSize = 0;
            this.iconButton2.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButton2.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButton2.ForeColor = System.Drawing.Color.White;
            this.iconButton2.IconChar = FontAwesome.Sharp.IconChar.WindowRestore;
            this.iconButton2.IconColor = System.Drawing.Color.White;
            this.iconButton2.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButton2.IconSize = 20;
            this.iconButton2.Location = new System.Drawing.Point(924, 4);
            this.iconButton2.Name = "iconButton2";
            this.iconButton2.Size = new System.Drawing.Size(30, 30);
            this.iconButton2.TabIndex = 12;
            this.iconButton2.UseVisualStyleBackColor = false;
            this.iconButton2.Click += new System.EventHandler(this.btnMax_Click);
            // 
            // iconButton3
            // 
            this.iconButton3.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.iconButton3.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.iconButton3.FlatAppearance.BorderSize = 0;
            this.iconButton3.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButton3.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButton3.ForeColor = System.Drawing.Color.White;
            this.iconButton3.IconChar = FontAwesome.Sharp.IconChar.X;
            this.iconButton3.IconColor = System.Drawing.Color.White;
            this.iconButton3.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButton3.IconSize = 20;
            this.iconButton3.Location = new System.Drawing.Point(952, 4);
            this.iconButton3.Name = "iconButton3";
            this.iconButton3.Size = new System.Drawing.Size(30, 30);
            this.iconButton3.TabIndex = 11;
            this.iconButton3.UseVisualStyleBackColor = false;
            this.iconButton3.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // iconCurrentChildForm
            // 
            this.iconCurrentChildForm.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.iconCurrentChildForm.IconChar = FontAwesome.Sharp.IconChar.UserTie;
            this.iconCurrentChildForm.IconColor = System.Drawing.Color.White;
            this.iconCurrentChildForm.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconCurrentChildForm.IconSize = 62;
            this.iconCurrentChildForm.Location = new System.Drawing.Point(41, 31);
            this.iconCurrentChildForm.Name = "iconCurrentChildForm";
            this.iconCurrentChildForm.Size = new System.Drawing.Size(62, 63);
            this.iconCurrentChildForm.TabIndex = 9;
            this.iconCurrentChildForm.TabStop = false;
            // 
            // lblUsername
            // 
            this.lblUsername.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Bottom | System.Windows.Forms.AnchorStyles.Right)));
            this.lblUsername.AutoSize = true;
            this.lblUsername.BackColor = System.Drawing.Color.Transparent;
            this.lblUsername.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.lblUsername.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblUsername.ForeColor = System.Drawing.Color.White;
            this.lblUsername.Location = new System.Drawing.Point(874, 66);
            this.lblUsername.Name = "lblUsername";
            this.lblUsername.Size = new System.Drawing.Size(85, 19);
            this.lblUsername.TabIndex = 8;
            this.lblUsername.Text = "username";
            this.lblUsername.TextAlign = System.Drawing.ContentAlignment.TopCenter;
            // 
            // lblName
            // 
            this.lblName.AutoSize = true;
            this.lblName.Font = new System.Drawing.Font("Arial", 24F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.lblName.ForeColor = System.Drawing.Color.White;
            this.lblName.Location = new System.Drawing.Point(109, 49);
            this.lblName.Name = "lblName";
            this.lblName.Size = new System.Drawing.Size(80, 36);
            this.lblName.TabIndex = 6;
            this.lblName.Text = "Club";
            // 
            // btnMax
            // 
            this.btnMax.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnMax.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnMax.FlatAppearance.BorderSize = 0;
            this.btnMax.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnMax.ForeColor = System.Drawing.Color.White;
            this.btnMax.Location = new System.Drawing.Point(943, 5);
            this.btnMax.Name = "btnMax";
            this.btnMax.Size = new System.Drawing.Size(16, 17);
            this.btnMax.TabIndex = 5;
            this.btnMax.UseVisualStyleBackColor = true;
            this.btnMax.Click += new System.EventHandler(this.btnMax_Click);
            // 
            // btnMinus
            // 
            this.btnMinus.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnMinus.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnMinus.FlatAppearance.BorderSize = 0;
            this.btnMinus.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnMinus.ForeColor = System.Drawing.Color.White;
            this.btnMinus.Location = new System.Drawing.Point(921, 5);
            this.btnMinus.Name = "btnMinus";
            this.btnMinus.Size = new System.Drawing.Size(16, 17);
            this.btnMinus.TabIndex = 4;
            this.btnMinus.UseVisualStyleBackColor = true;
            this.btnMinus.Click += new System.EventHandler(this.btnMinus_Click);
            // 
            // btnExit
            // 
            this.btnExit.Anchor = ((System.Windows.Forms.AnchorStyles)((System.Windows.Forms.AnchorStyles.Top | System.Windows.Forms.AnchorStyles.Right)));
            this.btnExit.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Zoom;
            this.btnExit.FlatAppearance.BorderSize = 0;
            this.btnExit.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnExit.ForeColor = System.Drawing.Color.White;
            this.btnExit.Location = new System.Drawing.Point(965, 5);
            this.btnExit.Name = "btnExit";
            this.btnExit.Size = new System.Drawing.Size(16, 17);
            this.btnExit.TabIndex = 3;
            this.btnExit.UseVisualStyleBackColor = true;
            this.btnExit.Click += new System.EventHandler(this.btnExit_Click);
            // 
            // panelMenu
            // 
            this.panelMenu.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.panelMenu.Controls.Add(this.iconButtonProfile);
            this.panelMenu.Controls.Add(this.iconButtonShop);
            this.panelMenu.Controls.Add(this.iconButtonAdmin);
            this.panelMenu.Controls.Add(this.iconButtonContact);
            this.panelMenu.Controls.Add(this.iconButtonCalendar);
            this.panelMenu.Controls.Add(this.iconButtonTrainers);
            this.panelMenu.Controls.Add(this.iconButtonEvent);
            this.panelMenu.Controls.Add(this.iconButtonRegister);
            this.panelMenu.Controls.Add(this.iconButtonAtleta);
            this.panelMenu.Controls.Add(this.iconButtonLogout);
            this.panelMenu.Controls.Add(this.panelFoto);
            this.panelMenu.Dock = System.Windows.Forms.DockStyle.Left;
            this.panelMenu.Location = new System.Drawing.Point(0, 0);
            this.panelMenu.Name = "panelMenu";
            this.panelMenu.Size = new System.Drawing.Size(197, 861);
            this.panelMenu.TabIndex = 2;
            // 
            // iconButtonProfile
            // 
            this.iconButtonProfile.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonProfile.FlatAppearance.BorderSize = 0;
            this.iconButtonProfile.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonProfile.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonProfile.ForeColor = System.Drawing.Color.White;
            this.iconButtonProfile.IconChar = FontAwesome.Sharp.IconChar.UserAlt;
            this.iconButtonProfile.IconColor = System.Drawing.Color.White;
            this.iconButtonProfile.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonProfile.IconSize = 32;
            this.iconButtonProfile.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonProfile.Location = new System.Drawing.Point(0, 594);
            this.iconButtonProfile.Name = "iconButtonProfile";
            this.iconButtonProfile.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonProfile.Size = new System.Drawing.Size(197, 57);
            this.iconButtonProfile.TabIndex = 14;
            this.iconButtonProfile.Text = "Perfil";
            this.iconButtonProfile.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonProfile.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonProfile.UseVisualStyleBackColor = false;
            this.iconButtonProfile.Click += new System.EventHandler(this.iconButtonProfile_Click);
            // 
            // iconButtonShop
            // 
            this.iconButtonShop.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonShop.FlatAppearance.BorderSize = 0;
            this.iconButtonShop.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonShop.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonShop.ForeColor = System.Drawing.Color.White;
            this.iconButtonShop.IconChar = FontAwesome.Sharp.IconChar.StoreAlt;
            this.iconButtonShop.IconColor = System.Drawing.Color.White;
            this.iconButtonShop.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonShop.IconSize = 32;
            this.iconButtonShop.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonShop.Location = new System.Drawing.Point(0, 534);
            this.iconButtonShop.Name = "iconButtonShop";
            this.iconButtonShop.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonShop.Size = new System.Drawing.Size(197, 60);
            this.iconButtonShop.TabIndex = 12;
            this.iconButtonShop.Text = "Tienda";
            this.iconButtonShop.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonShop.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonShop.UseVisualStyleBackColor = true;
            this.iconButtonShop.Click += new System.EventHandler(this.iconButtonShop_Click);
            // 
            // iconButtonAdmin
            // 
            this.iconButtonAdmin.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonAdmin.FlatAppearance.BorderSize = 0;
            this.iconButtonAdmin.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonAdmin.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonAdmin.ForeColor = System.Drawing.Color.White;
            this.iconButtonAdmin.IconChar = FontAwesome.Sharp.IconChar.NetworkWired;
            this.iconButtonAdmin.IconColor = System.Drawing.Color.White;
            this.iconButtonAdmin.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonAdmin.IconSize = 32;
            this.iconButtonAdmin.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonAdmin.Location = new System.Drawing.Point(0, 477);
            this.iconButtonAdmin.Name = "iconButtonAdmin";
            this.iconButtonAdmin.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonAdmin.Size = new System.Drawing.Size(197, 57);
            this.iconButtonAdmin.TabIndex = 2;
            this.iconButtonAdmin.Text = "Administración";
            this.iconButtonAdmin.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonAdmin.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonAdmin.UseVisualStyleBackColor = false;
            this.iconButtonAdmin.Click += new System.EventHandler(this.iconButtonAdmin_Click);
            // 
            // iconButtonContact
            // 
            this.iconButtonContact.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonContact.FlatAppearance.BorderSize = 0;
            this.iconButtonContact.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonContact.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonContact.ForeColor = System.Drawing.Color.White;
            this.iconButtonContact.IconChar = FontAwesome.Sharp.IconChar.AddressCard;
            this.iconButtonContact.IconColor = System.Drawing.Color.White;
            this.iconButtonContact.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonContact.IconSize = 32;
            this.iconButtonContact.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonContact.Location = new System.Drawing.Point(0, 420);
            this.iconButtonContact.Name = "iconButtonContact";
            this.iconButtonContact.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonContact.Size = new System.Drawing.Size(197, 57);
            this.iconButtonContact.TabIndex = 6;
            this.iconButtonContact.Text = "Contactos";
            this.iconButtonContact.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonContact.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonContact.UseVisualStyleBackColor = false;
            this.iconButtonContact.Click += new System.EventHandler(this.iconButtonContact_Click);
            // 
            // iconButtonCalendar
            // 
            this.iconButtonCalendar.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonCalendar.FlatAppearance.BorderSize = 0;
            this.iconButtonCalendar.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonCalendar.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonCalendar.ForeColor = System.Drawing.Color.White;
            this.iconButtonCalendar.IconChar = FontAwesome.Sharp.IconChar.CalendarAlt;
            this.iconButtonCalendar.IconColor = System.Drawing.Color.White;
            this.iconButtonCalendar.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonCalendar.IconSize = 32;
            this.iconButtonCalendar.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonCalendar.Location = new System.Drawing.Point(0, 363);
            this.iconButtonCalendar.Name = "iconButtonCalendar";
            this.iconButtonCalendar.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonCalendar.Size = new System.Drawing.Size(197, 57);
            this.iconButtonCalendar.TabIndex = 3;
            this.iconButtonCalendar.Text = "Calendario";
            this.iconButtonCalendar.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonCalendar.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonCalendar.UseVisualStyleBackColor = false;
            this.iconButtonCalendar.Click += new System.EventHandler(this.iconButtonCalendar_Click);
            // 
            // iconButtonTrainers
            // 
            this.iconButtonTrainers.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonTrainers.FlatAppearance.BorderSize = 0;
            this.iconButtonTrainers.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonTrainers.ForeColor = System.Drawing.Color.White;
            this.iconButtonTrainers.IconChar = FontAwesome.Sharp.IconChar.PeopleGroup;
            this.iconButtonTrainers.IconColor = System.Drawing.Color.White;
            this.iconButtonTrainers.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonTrainers.IconSize = 32;
            this.iconButtonTrainers.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonTrainers.Location = new System.Drawing.Point(0, 306);
            this.iconButtonTrainers.Name = "iconButtonTrainers";
            this.iconButtonTrainers.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonTrainers.Size = new System.Drawing.Size(197, 57);
            this.iconButtonTrainers.TabIndex = 15;
            this.iconButtonTrainers.Text = "Entrenadores";
            this.iconButtonTrainers.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonTrainers.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonTrainers.UseVisualStyleBackColor = false;
            this.iconButtonTrainers.Click += new System.EventHandler(this.iconButtonTrainers_Click);
            // 
            // iconButtonEvent
            // 
            this.iconButtonEvent.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonEvent.FlatAppearance.BorderSize = 0;
            this.iconButtonEvent.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonEvent.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonEvent.ForeColor = System.Drawing.Color.White;
            this.iconButtonEvent.IconChar = FontAwesome.Sharp.IconChar.Medal;
            this.iconButtonEvent.IconColor = System.Drawing.Color.White;
            this.iconButtonEvent.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonEvent.IconSize = 32;
            this.iconButtonEvent.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonEvent.Location = new System.Drawing.Point(0, 249);
            this.iconButtonEvent.Name = "iconButtonEvent";
            this.iconButtonEvent.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonEvent.Size = new System.Drawing.Size(197, 57);
            this.iconButtonEvent.TabIndex = 11;
            this.iconButtonEvent.Text = "Eventos";
            this.iconButtonEvent.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonEvent.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonEvent.UseVisualStyleBackColor = true;
            this.iconButtonEvent.Click += new System.EventHandler(this.iconButtonEvent_Click);
            // 
            // iconButtonRegister
            // 
            this.iconButtonRegister.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonRegister.FlatAppearance.BorderSize = 0;
            this.iconButtonRegister.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonRegister.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonRegister.ForeColor = System.Drawing.Color.White;
            this.iconButtonRegister.IconChar = FontAwesome.Sharp.IconChar.UserPlus;
            this.iconButtonRegister.IconColor = System.Drawing.Color.White;
            this.iconButtonRegister.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonRegister.IconSize = 32;
            this.iconButtonRegister.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonRegister.Location = new System.Drawing.Point(0, 192);
            this.iconButtonRegister.Name = "iconButtonRegister";
            this.iconButtonRegister.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonRegister.Size = new System.Drawing.Size(197, 57);
            this.iconButtonRegister.TabIndex = 9;
            this.iconButtonRegister.Text = "Registrar";
            this.iconButtonRegister.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonRegister.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonRegister.UseVisualStyleBackColor = false;
            this.iconButtonRegister.Click += new System.EventHandler(this.iconButtonRegister_Click);
            // 
            // iconButtonAtleta
            // 
            this.iconButtonAtleta.Dock = System.Windows.Forms.DockStyle.Top;
            this.iconButtonAtleta.FlatAppearance.BorderSize = 0;
            this.iconButtonAtleta.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonAtleta.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonAtleta.ForeColor = System.Drawing.Color.White;
            this.iconButtonAtleta.IconChar = FontAwesome.Sharp.IconChar.Running;
            this.iconButtonAtleta.IconColor = System.Drawing.Color.White;
            this.iconButtonAtleta.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonAtleta.IconSize = 32;
            this.iconButtonAtleta.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonAtleta.Location = new System.Drawing.Point(0, 135);
            this.iconButtonAtleta.Name = "iconButtonAtleta";
            this.iconButtonAtleta.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonAtleta.Size = new System.Drawing.Size(197, 57);
            this.iconButtonAtleta.TabIndex = 1;
            this.iconButtonAtleta.Text = "Atletas";
            this.iconButtonAtleta.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonAtleta.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonAtleta.UseVisualStyleBackColor = false;
            this.iconButtonAtleta.Click += new System.EventHandler(this.iconButtonAtleta_Click);
            // 
            // iconButtonLogout
            // 
            this.iconButtonLogout.Dock = System.Windows.Forms.DockStyle.Bottom;
            this.iconButtonLogout.FlatAppearance.BorderSize = 0;
            this.iconButtonLogout.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.iconButtonLogout.Font = new System.Drawing.Font("Arial", 9.75F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.iconButtonLogout.ForeColor = System.Drawing.Color.White;
            this.iconButtonLogout.IconChar = FontAwesome.Sharp.IconChar.PersonThroughWindow;
            this.iconButtonLogout.IconColor = System.Drawing.Color.White;
            this.iconButtonLogout.IconFont = FontAwesome.Sharp.IconFont.Auto;
            this.iconButtonLogout.IconSize = 32;
            this.iconButtonLogout.ImageAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonLogout.Location = new System.Drawing.Point(0, 804);
            this.iconButtonLogout.Name = "iconButtonLogout";
            this.iconButtonLogout.Padding = new System.Windows.Forms.Padding(10, 0, 20, 0);
            this.iconButtonLogout.Size = new System.Drawing.Size(197, 57);
            this.iconButtonLogout.TabIndex = 5;
            this.iconButtonLogout.Text = "Cerrar";
            this.iconButtonLogout.TextAlign = System.Drawing.ContentAlignment.MiddleLeft;
            this.iconButtonLogout.TextImageRelation = System.Windows.Forms.TextImageRelation.ImageBeforeText;
            this.iconButtonLogout.UseVisualStyleBackColor = false;
            this.iconButtonLogout.Click += new System.EventHandler(this.iconButtonLogout_Click);
            // 
            // panelFoto
            // 
            this.panelFoto.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.panelFoto.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.panelFoto.Controls.Add(this.pictureBox1);
            this.panelFoto.Dock = System.Windows.Forms.DockStyle.Top;
            this.panelFoto.Location = new System.Drawing.Point(0, 0);
            this.panelFoto.Name = "panelFoto";
            this.panelFoto.Size = new System.Drawing.Size(197, 135);
            this.panelFoto.TabIndex = 0;
            // 
            // pictureBox1
            // 
            this.pictureBox1.BackgroundImage = global::InterfazRest.Properties.Resources.atletismo;
            this.pictureBox1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.pictureBox1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.pictureBox1.ErrorImage = null;
            this.pictureBox1.Location = new System.Drawing.Point(0, 0);
            this.pictureBox1.Name = "pictureBox1";
            this.pictureBox1.Size = new System.Drawing.Size(197, 135);
            this.pictureBox1.TabIndex = 0;
            this.pictureBox1.TabStop = false;
            this.pictureBox1.Click += new System.EventHandler(this.pictureBox1_Click);
            // 
            // panelDesktop
            // 
            this.panelDesktop.BackColor = System.Drawing.Color.Transparent;
            this.panelDesktop.BackgroundImage = global::InterfazRest.Properties.Resources.fondo_transparencia;
            this.panelDesktop.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.panelDesktop.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelDesktop.Location = new System.Drawing.Point(197, 100);
            this.panelDesktop.Name = "panelDesktop";
            this.panelDesktop.Size = new System.Drawing.Size(987, 761);
            this.panelDesktop.TabIndex = 4;
            // 
            // HomeAdminForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.White;
            this.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.ClientSize = new System.Drawing.Size(1184, 861);
            this.Controls.Add(this.panelDesktop);
            this.Controls.Add(this.panelTitleBar);
            this.Controls.Add(this.panelMenu);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.None;
            this.Name = "HomeAdminForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.panelTitleBar.ResumeLayout(false);
            this.panelTitleBar.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.iconCurrentChildForm)).EndInit();
            this.panelMenu.ResumeLayout(false);
            this.panelFoto.ResumeLayout(false);
            ((System.ComponentModel.ISupportInitialize)(this.pictureBox1)).EndInit();
            this.ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel panelTitleBar;
        private System.Windows.Forms.Label lblName;
        private System.Windows.Forms.Panel panelMenu;
        private FontAwesome.Sharp.IconButton iconButtonLogout;
        private FontAwesome.Sharp.IconButton iconButtonCalendar;
        private FontAwesome.Sharp.IconButton iconButtonContact;
        private FontAwesome.Sharp.IconButton iconButtonRegister;
        private System.Windows.Forms.Panel panelDesktop;
        private FontAwesome.Sharp.IconButton iconButtonShop;
        private FontAwesome.Sharp.IconButton iconButtonEvent;
        private FontAwesome.Sharp.IconButton iconButtonProfile;
        private FontAwesome.Sharp.IconButton iconButtonAtleta;
        private FontAwesome.Sharp.IconButton iconButtonAdmin;
        private FontAwesome.Sharp.IconButton iconButtonTrainers;
        private System.Windows.Forms.Panel panelFoto;
        private System.Windows.Forms.PictureBox pictureBox1;
        private System.Windows.Forms.Label lblUsername;
        private System.Windows.Forms.Button btnMax;
        private System.Windows.Forms.Button btnMinus;
        private System.Windows.Forms.Button btnExit;
        private FontAwesome.Sharp.IconPictureBox iconCurrentChildForm;
        private FontAwesome.Sharp.IconButton iconButton1;
        private FontAwesome.Sharp.IconButton iconButton2;
        private FontAwesome.Sharp.IconButton iconButton3;
    }
}
