using FontAwesome.Sharp;
using System;
using System.Drawing;
using System.Windows.Forms;

namespace InterfazRest.Utilities
{

    public static class FormUtilities
    {
        /// <summary>
        /// Activa visualmente un botón en la interfaz, cambiando su color, estilo y configurando el borde izquierdo.
        /// También actualiza el icono del formulario hijo actual.
        /// </summary>
        /// <param name="currentBtn">El botón que se activará.</param>
        /// <param name="leftBorderBtn">El panel que representa el borde izquierdo del botón activo.</param>
        /// <param name="iconCurrentChildForm">El icono del formulario hijo que se actualizará.</param>
        /// <param name="lblSeleccion">El label donde se actualizará el título del formulario hijo.</param>
        /// <param name="defaultColor">El color predeterminado del tema.</param>
        /// <param name="activeColor">El color activo del tema.</param>
        public static void ActivateButton(
            IconButton currentBtn,
            Panel leftBorderBtn,
            IconPictureBox iconCurrentChildForm,
            Label lblSeleccion,
            Color defaultColor,
            Color activeColor)
        {
            if (currentBtn != null)
            {
                DisableButton(currentBtn, defaultColor);

                // Configura el botón activo
                currentBtn.BackColor = defaultColor;
                currentBtn.ForeColor = activeColor;
                currentBtn.TextAlign = ContentAlignment.MiddleCenter;
                currentBtn.IconColor = activeColor;
                currentBtn.TextImageRelation = TextImageRelation.TextBeforeImage;
                currentBtn.ImageAlign = ContentAlignment.MiddleRight;

                // Configura el borde izquierdo del botón activo
                leftBorderBtn.BackColor = activeColor;
                leftBorderBtn.Location = new Point(0, currentBtn.Location.Y);
                leftBorderBtn.Visible = true;
                leftBorderBtn.BringToFront();

                // Actualiza el icono del formulario hijo actual
                iconCurrentChildForm.IconChar = currentBtn.IconChar;
                iconCurrentChildForm.IconColor = activeColor;
                lblSeleccion.ForeColor = activeColor;
            }
        }

        /// <summary>
        /// Desactiva visualmente el botón actualmente activo, restaurando su apariencia original.
        /// </summary>
        /// <param name="currentBtn">El botón que se desactivará.</param>
        /// <param name="defaultColor">El color predeterminado para restaurar el botón.</param>
        public static void DisableButton(IconButton currentBtn, Color defaultColor)
        {
            if (currentBtn != null)
            {
                // Restablece el estilo original del botón
                currentBtn.BackColor = defaultColor;
                currentBtn.ForeColor = Color.Gainsboro;
                currentBtn.TextAlign = ContentAlignment.MiddleLeft;
                currentBtn.IconColor = Color.Gainsboro;
                currentBtn.TextImageRelation = TextImageRelation.ImageBeforeText;
                currentBtn.ImageAlign = ContentAlignment.MiddleLeft;
            }
        }

        /// <summary>
        /// Abre un formulario hijo dentro del panel principal, cerrando el formulario hijo anterior si existe.
        /// </summary>
        /// <param name="currentChildForm">Referencia al formulario hijo actualmente abierto.</param>
        /// <param name="childForm">El nuevo formulario hijo que se abrirá.</param>
        /// <param name="panelDesktop">El panel donde se mostrará el formulario hijo.</param>
        /// <param name="lblTitleChildForm">La etiqueta donde se mostrará el título del formulario hijo.</param>
        public static void OpenChildForm(
            ref Form currentChildForm,
            Form childForm,
            Panel panelDesktop,
            Label lblTitleChildForm)
        {
            currentChildForm?.Close(); // Cierra el formulario actual si existe
            currentChildForm = childForm; // Asigna el nuevo formulario a la variable

            // Configuración del nuevo formulario hijo
            childForm.TopLevel = false;
            childForm.FormBorderStyle = FormBorderStyle.None;
            childForm.Dock = DockStyle.Fill;
            panelDesktop.Controls.Add(childForm);
            panelDesktop.Tag = childForm;
            childForm.BringToFront();
            childForm.Show();

            // Actualiza el título del formulario hijo actual si lblTitleChildForm no es null
            if (lblTitleChildForm != null)
            {
                lblTitleChildForm.Text = childForm.Text;
            }
        }

        /// <summary>
        /// Restablece el estado del formulario y del botón activo a su estado predeterminado.
        /// </summary>
        /// <param name="iconCurrentChildForm">El icono del formulario hijo que se restablecerá.</param>
        /// <param name="leftBorderBtn">El panel del borde izquierdo que se ocultará.</param>
        /// <param name="lblSeleccion">La etiqueta del título que se restablecerá.</param>
        /// <param name="resetText">Texto por defecto a mostrar en la etiqueta.</param>
        public static void Reset(
            IconPictureBox iconCurrentChildForm,
            Panel leftBorderBtn,
            Label lblTitleChildForm,
            string resetText)
        {
            // Oculta el borde del botón activo
            leftBorderBtn.Visible = false;

            // Restablece el icono del formulario hijo actual
            iconCurrentChildForm.IconChar = IconChar.Home;
            iconCurrentChildForm.IconColor = Color.White;

            // Cambia el título o texto de restablecimiento
            lblTitleChildForm.Text = resetText;
        }

        public static void InitializeMenuStrip(MenuStrip menuStrip, EventHandler onMouseEnter, EventHandler onMouseLeave, EventHandler onClick)
        {
            foreach (ToolStripMenuItem menuItem in menuStrip.Items)
            {
                menuItem.MouseEnter += onMouseEnter;
                menuItem.MouseLeave += onMouseLeave;
                menuItem.Click += onClick;
            }
        }

        /// <summary>
        /// Cambia el color de fondo y texto de los elementos del MenuStrip cuando el ratón entra o sale.
        /// </summary>
        /// <param name="menuItem">El elemento del menú actual.</param>
        /// <param name="currentSelectedItem">El elemento del menú actualmente seleccionado.</param>
        /// <param name="isMouseHover">Indica si el ratón está sobre el elemento o no.</param>
        public static void SetMenuStripItemHoverColor(ToolStripMenuItem menuItem, ToolStripMenuItem currentSelectedItem, bool isMouseHover)
        {
            if (menuItem != null && menuItem != currentSelectedItem)
            {
                if (isMouseHover)
                {
                    menuItem.BackColor = Color.FromArgb(254, 235, 160); // Color al pasar el ratón
                    menuItem.ForeColor = Color.FromArgb(121, 47, 18); // Color del texto al pasar el ratón
                }
                else
                {
                    menuItem.BackColor = Color.FromArgb(121, 47, 18); // Color original
                    menuItem.ForeColor = Color.White; // Color del texto original
                }
            }
        }

        /// <summary>
        /// Actualiza la selección del menú, cambiando el color del elemento seleccionado y restableciendo el color del elemento anterior.
        /// </summary>
        /// <param name="newSelectedItem">El nuevo elemento del menú que ha sido seleccionado.</param>
        /// <param name="currentSelectedItem">Referencia al elemento del menú actualmente seleccionado.</param>
        /// <param name="homeAdminForm">Formulario principal donde se actualizará el título o alguna otra propiedad.</param>
        public static void UpdateMenuSelection(ToolStripMenuItem newSelectedItem, ref ToolStripMenuItem currentSelectedItem, HomeAdminForm homeAdminForm)
        {
            // Restaura el estilo visual del elemento previamente seleccionado
            if (currentSelectedItem != null)
            {
                currentSelectedItem.BackColor = Color.FromArgb(121, 47, 18); // Color original
                currentSelectedItem.ForeColor = Color.White;
            }

            // Establece el nuevo elemento seleccionado
            currentSelectedItem = newSelectedItem;
            currentSelectedItem.BackColor = Color.FromArgb(254, 235, 160); // Color al ser seleccionado
            currentSelectedItem.ForeColor = Color.FromArgb(121, 47, 18);

            // Actualiza el título o alguna propiedad en el formulario principal
            homeAdminForm.UpdateTitle(newSelectedItem.Text);
        }

        // Método adicional para manejar el comportamiento de hover
        public static void SetMenuStripHoverBehavior(object sender, ToolStripMenuItem currentSelectedItem, bool isMouseHover)
        {
            SetMenuStripItemHoverColor(sender as ToolStripMenuItem, currentSelectedItem, isMouseHover);
        }

        // Método adicional para manejar el click en MenuStripItem
        public static void HandleMenuStripItemClick(object sender, ref ToolStripMenuItem currentSelectedItem, HomeAdminForm homeAdminForm)
        {
            UpdateMenuSelection(sender as ToolStripMenuItem, ref currentSelectedItem, homeAdminForm);
        }

        // Renderer personalizado para el MenuStrip
        public class MyMenuRenderer : ToolStripProfessionalRenderer
        {
            protected override void OnRenderMenuItemBackground(ToolStripItemRenderEventArgs e)
            {
                Color backColor = e.Item.Selected
                    ? Color.FromArgb(254, 235, 160)
                    : Color.FromArgb(121, 47, 18);
                e.Graphics.FillRectangle(new SolidBrush(backColor), e.Item.ContentRectangle);
            }

            protected override void OnRenderItemText(ToolStripItemTextRenderEventArgs e)
            {
                e.TextColor = e.Item.Selected ? Color.FromArgb(121, 47, 18) : Color.White;
                base.OnRenderItemText(e);
            }
        }

    }
}



