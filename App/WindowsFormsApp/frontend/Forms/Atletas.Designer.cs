namespace InterfazRest
{
    partial class Atletas
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            this.panelBase = new System.Windows.Forms.Panel();
            this.panel1 = new System.Windows.Forms.Panel();
            this.menuStrip1 = new System.Windows.Forms.MenuStrip();
            this.perfilToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.actualizarDatosToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.restablecerContraseñaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.consultasToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.listarTodosLosAtletasToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.listarPorCategoríaToolStripMenuItem = new System.Windows.Forms.ToolStripMenuItem();
            this.panelBase.SuspendLayout();
            this.menuStrip1.SuspendLayout();
            this.SuspendLayout();
            // 
            // panelBase
            // 
            this.panelBase.BackColor = System.Drawing.Color.White;
            this.panelBase.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.panelBase.Controls.Add(this.panel1);
            this.panelBase.Controls.Add(this.menuStrip1);
            this.panelBase.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panelBase.Location = new System.Drawing.Point(0, 0);
            this.panelBase.Name = "panelBase";
            this.panelBase.Size = new System.Drawing.Size(987, 761);
            this.panelBase.TabIndex = 15;
            // 
            // panel1
            // 
            this.panel1.BackgroundImage = global::InterfazRest.Properties.Resources.fondo_transparencia;
            this.panel1.BackgroundImageLayout = System.Windows.Forms.ImageLayout.Stretch;
            this.panel1.Dock = System.Windows.Forms.DockStyle.Fill;
            this.panel1.Location = new System.Drawing.Point(0, 26);
            this.panel1.Name = "panel1";
            this.panel1.Size = new System.Drawing.Size(987, 735);
            this.panel1.TabIndex = 30;
            // 
            // menuStrip1
            // 
            this.menuStrip1.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.menuStrip1.Font = new System.Drawing.Font("Arial", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.menuStrip1.GripStyle = System.Windows.Forms.ToolStripGripStyle.Visible;
            this.menuStrip1.ImeMode = System.Windows.Forms.ImeMode.NoControl;
            this.menuStrip1.Items.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.perfilToolStripMenuItem,
            this.consultasToolStripMenuItem});
            this.menuStrip1.LayoutStyle = System.Windows.Forms.ToolStripLayoutStyle.Flow;
            this.menuStrip1.Location = new System.Drawing.Point(0, 0);
            this.menuStrip1.Name = "menuStrip1";
            this.menuStrip1.Size = new System.Drawing.Size(987, 26);
            this.menuStrip1.TabIndex = 29;
            // 
            // perfilToolStripMenuItem
            // 
            this.perfilToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.perfilToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.actualizarDatosToolStripMenuItem,
            this.restablecerContraseñaToolStripMenuItem});
            this.perfilToolStripMenuItem.ForeColor = System.Drawing.Color.White;
            this.perfilToolStripMenuItem.Name = "perfilToolStripMenuItem";
            this.perfilToolStripMenuItem.Size = new System.Drawing.Size(68, 22);
            this.perfilToolStripMenuItem.Text = "Atletas";
            this.perfilToolStripMenuItem.Click += new System.EventHandler(this.MenuStripItem_Click);
            this.perfilToolStripMenuItem.MouseEnter += new System.EventHandler(this.MenuStripItem_MouseEnter);
            this.perfilToolStripMenuItem.MouseLeave += new System.EventHandler(this.MenuStripItem_MouseLeave);
            // 
            // actualizarDatosToolStripMenuItem
            // 
            this.actualizarDatosToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.actualizarDatosToolStripMenuItem.ForeColor = System.Drawing.Color.White;
            this.actualizarDatosToolStripMenuItem.Name = "actualizarDatosToolStripMenuItem";
            this.actualizarDatosToolStripMenuItem.Size = new System.Drawing.Size(245, 22);
            this.actualizarDatosToolStripMenuItem.Text = "Actualizar Datos";
            this.actualizarDatosToolStripMenuItem.Click += new System.EventHandler(this.MenuStripItem_Click);
            this.actualizarDatosToolStripMenuItem.MouseEnter += new System.EventHandler(this.MenuStripItem_MouseEnter);
            this.actualizarDatosToolStripMenuItem.MouseLeave += new System.EventHandler(this.MenuStripItem_MouseLeave);
            // 
            // restablecerContraseñaToolStripMenuItem
            // 
            this.restablecerContraseñaToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.restablecerContraseñaToolStripMenuItem.ForeColor = System.Drawing.Color.White;
            this.restablecerContraseñaToolStripMenuItem.Name = "restablecerContraseñaToolStripMenuItem";
            this.restablecerContraseñaToolStripMenuItem.Size = new System.Drawing.Size(245, 22);
            this.restablecerContraseñaToolStripMenuItem.Text = "Restablecer Contraseña";
            this.restablecerContraseñaToolStripMenuItem.Click += new System.EventHandler(this.restablecerContraseñaToolStripMenuItem_Click);
            this.restablecerContraseñaToolStripMenuItem.MouseEnter += new System.EventHandler(this.MenuStripItem_MouseEnter);
            this.restablecerContraseñaToolStripMenuItem.MouseLeave += new System.EventHandler(this.MenuStripItem_MouseLeave);
            // 
            // consultasToolStripMenuItem
            // 
            this.consultasToolStripMenuItem.DropDownItems.AddRange(new System.Windows.Forms.ToolStripItem[] {
            this.listarTodosLosAtletasToolStripMenuItem,
            this.listarPorCategoríaToolStripMenuItem});
            this.consultasToolStripMenuItem.ForeColor = System.Drawing.Color.White;
            this.consultasToolStripMenuItem.Name = "consultasToolStripMenuItem";
            this.consultasToolStripMenuItem.Size = new System.Drawing.Size(89, 22);
            this.consultasToolStripMenuItem.Text = "Consultas";
            this.consultasToolStripMenuItem.Click += new System.EventHandler(this.MenuStripItem_Click);
            this.consultasToolStripMenuItem.MouseEnter += new System.EventHandler(this.MenuStripItem_MouseEnter);
            this.consultasToolStripMenuItem.MouseLeave += new System.EventHandler(this.MenuStripItem_MouseLeave);
            // 
            // listarTodosLosAtletasToolStripMenuItem
            // 
            this.listarTodosLosAtletasToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.listarTodosLosAtletasToolStripMenuItem.ForeColor = System.Drawing.Color.White;
            this.listarTodosLosAtletasToolStripMenuItem.Name = "listarTodosLosAtletasToolStripMenuItem";
            this.listarTodosLosAtletasToolStripMenuItem.Size = new System.Drawing.Size(233, 22);
            this.listarTodosLosAtletasToolStripMenuItem.Text = "Listar todos los Atletas";
            this.listarTodosLosAtletasToolStripMenuItem.Click += new System.EventHandler(this.MenuStripItem_Click);
            this.listarTodosLosAtletasToolStripMenuItem.MouseEnter += new System.EventHandler(this.MenuStripItem_MouseEnter);
            this.listarTodosLosAtletasToolStripMenuItem.MouseLeave += new System.EventHandler(this.MenuStripItem_MouseLeave);
            // 
            // listarPorCategoríaToolStripMenuItem
            // 
            this.listarPorCategoríaToolStripMenuItem.BackColor = System.Drawing.Color.FromArgb(((int)(((byte)(121)))), ((int)(((byte)(47)))), ((int)(((byte)(18)))));
            this.listarPorCategoríaToolStripMenuItem.ForeColor = System.Drawing.Color.White;
            this.listarPorCategoríaToolStripMenuItem.Name = "listarPorCategoríaToolStripMenuItem";
            this.listarPorCategoríaToolStripMenuItem.Size = new System.Drawing.Size(233, 22);
            this.listarPorCategoríaToolStripMenuItem.Text = "Listar por Categoría";
            this.listarPorCategoríaToolStripMenuItem.Click += new System.EventHandler(this.MenuStripItem_Click);
            this.listarPorCategoríaToolStripMenuItem.MouseEnter += new System.EventHandler(this.MenuStripItem_MouseEnter);
            this.listarPorCategoríaToolStripMenuItem.MouseLeave += new System.EventHandler(this.MenuStripItem_MouseLeave);
            // 
            // Atletas
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.ClientSize = new System.Drawing.Size(987, 761);
            this.Controls.Add(this.panelBase);
            this.MainMenuStrip = this.menuStrip1;
            this.Name = "Atletas";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterParent;
            this.Text = "Atletas";
            this.panelBase.ResumeLayout(false);
            this.panelBase.PerformLayout();
            this.menuStrip1.ResumeLayout(false);
            this.menuStrip1.PerformLayout();
            this.ResumeLayout(false);

        }

        #endregion
        private System.Windows.Forms.Panel panelBase;
        private System.Windows.Forms.MenuStrip menuStrip1;
        private System.Windows.Forms.ToolStripMenuItem perfilToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem actualizarDatosToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem restablecerContraseñaToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem consultasToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem listarTodosLosAtletasToolStripMenuItem;
        private System.Windows.Forms.ToolStripMenuItem listarPorCategoríaToolStripMenuItem;
        private System.Windows.Forms.Panel panel1;
    }
}