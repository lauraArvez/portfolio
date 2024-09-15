using System;
using System.Runtime.InteropServices;
using System.Windows.Forms;

namespace InterfazRest.Utilities
{
    public static class FormActions
    {
        /// <summary>
        /// Cierra la aplicación.
        /// </summary>
        public static void ExitApplication()
        {
            Application.Exit();
        }

        /// <summary>
        /// Alterna el estado de maximizar/restaurar de la ventana.
        /// </summary>
        /// <param name="form">Formulario actual.</param>
        public static void ToggleMaximize(Form form)
        {
            form.WindowState = form.WindowState == FormWindowState.Normal
                ? FormWindowState.Maximized
                : FormWindowState.Normal;
        }

        /// <summary>
        /// Minimiza la ventana.
        /// </summary>
        /// <param name="form">Formulario actual.</param>
        public static void Minimize(Form form)
        {
            form.WindowState = FormWindowState.Minimized;
        }

        /// <summary>
        /// Libera la captura del ratón para permitir arrastrar la ventana desde la barra de título.
        /// </summary>
        [DllImport("user32.DLL", EntryPoint = "ReleaseCapture")]
        public static extern void ReleaseCapture();

        /// <summary>
        /// Envía un mensaje para permitir el movimiento de la ventana desde la barra de título.
        /// </summary>
        [DllImport("user32.DLL", EntryPoint = "SendMessage")]
        public static extern void SendMessage(IntPtr hWnd, int wMsg, int wParam, int lParam);

        /// <summary>
        /// Maneja el arrastre del formulario desde la barra de título.
        /// </summary>
        /// <param name="form">Formulario actual.</param>
        public static void HandleTitleBarDrag(Form form)
        {
            ReleaseCapture();
            SendMessage(form.Handle, 0x112, 0xf012, 0);
        }
    }
}
