using InterfazRest.Services;
using InterfazRest.Utilities;
using System;
using System.Collections.Generic;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InterfazRest
{
    /// <summary>
    /// Formulario para la gestión de Atletas dentro de la aplicación. 
    /// Permite la navegación a diferentes secciones mediante un MenuStrip.
    /// </summary>
    public partial class Atletas : Form
    {
        // Variable para el formulario hijo
        private Form currentChildForm; 

        // Opción actualmente seleccionada en el MenuStrip
        private ToolStripMenuItem currentSelectedItem;

        // Referencia al formulario principal de administración
        private HomeAdminForm homeAdminForm;

        // Servicio de autenticación inyectado
        private readonly IAuthService _authService; 

        /// <summary>
        /// Constructor que inicializa una nueva instancia de la clase <see cref="Atletas"/>
        /// </summary>
        /// <param name="homeAdminForm">Formulario principal de administración.</param>
        /// <param name="authService">Servicio de autenticación inyectado.</param>
        public Atletas(HomeAdminForm homeAdminForm, IAuthService authService)
        {
            InitializeComponent();
            this.homeAdminForm = homeAdminForm;
            _authService = authService;

            // Asocia eventos de interacción y establece un renderer personalizado para el MenuStrip
            InitializeMenuStrip();            
        }
       
        /// <summary>
        /// Inicializa los eventos y el estilo del MenuStrip utilizando los métodos de FormUtilities.
        /// </summary>
        private void InitializeMenuStrip()
        {
            FormUtilities.InitializeMenuStrip(menuStrip1,
                MenuStripItem_MouseEnter,
                MenuStripItem_MouseLeave,
                MenuStripItem_Click);

            // Establece un renderer personalizado para el MenuStrip
            menuStrip1.Renderer = new MyMenuRenderer();
        }

        /// <summary>
        /// Abre un formulario hijo dentro del panel principal, cerrando cualquier formulario hijo abierto previamente.
        /// </summary>
        /// <param name="childForm">Formulario hijo a abrir.</param>
        private void OpenChildForm(Form childForm)
        {
            FormUtilities.OpenChildForm(ref currentChildForm, childForm, panelBase, null);
        }

        /// <summary>
        /// Evento que se ejecuta cuando el mouse entra en un elemento del MenuStrip.
        /// Cambia el color del fondo y del texto del menú al pasar el ratón sobre él.
        /// </summary>
        private void MenuStripItem_MouseEnter(object sender, EventArgs e)
        {
            FormUtilities.SetMenuStripItemHoverColor(sender as ToolStripMenuItem, currentSelectedItem, true);
        }

        /// <summary>
        /// Evento que se ejecuta cuando el mouse sale de un elemento del MenuStrip.
        /// Restaura el color original del fondo y del texto del menú cuando el ratón sale.
        /// </summary>
        private void MenuStripItem_MouseLeave(object sender, EventArgs e)
        {
            FormUtilities.SetMenuStripItemHoverColor(sender as ToolStripMenuItem, currentSelectedItem, false);
        }

        /// <summary>
        /// Evento que se ejecuta cuando se hace clic en un elemento del MenuStrip.
        /// Cambia el estilo visual del elemento seleccionado y actualiza el título en HomeAdminForm.
        /// </summary>
        private void MenuStripItem_Click(object sender, EventArgs e)
        {
            FormUtilities.UpdateMenuSelection(sender as ToolStripMenuItem, ref currentSelectedItem, homeAdminForm);
        }

        /// <summary>
        /// Clase de renderer personalizado para controlar el color y estilo de los elementos del MenuStrip.
        /// </summary>
        public class MyMenuRenderer : ToolStripProfessionalRenderer
        {
            protected override void OnRenderMenuItemBackground(ToolStripItemRenderEventArgs e)
            {
                // Cambia el color de fondo del elemento seleccionado o no seleccionado
                Color backColor = e.Item.Selected
                    ? Color.FromArgb(254, 235, 160)
                    : Color.FromArgb(121, 47, 18);
                e.Graphics.FillRectangle(new SolidBrush(backColor), e.Item.ContentRectangle);
            }

            protected override void OnRenderItemText(ToolStripItemTextRenderEventArgs e)
            {
                // Cambia el color del texto del elemento seleccionado o no seleccionado
                e.TextColor = e.Item.Selected ? Color.FromArgb(121, 47, 18) : Color.White;
                base.OnRenderItemText(e);
            }
        }

        /// <summary>
        /// Evento que se ejecuta al hacer clic en la opción del menú para restablecer la contraseña.
        /// Abre el formulario de restablecimiento de contraseña.
        /// </summary>
        private void restablecerContraseñaToolStripMenuItem_Click(object sender, EventArgs e)
        {
            OpenChildForm(new ResetPasswordForm(homeAdminForm, _authService));
            MenuStripItem_Click(sender, e);
        }
    }
}


