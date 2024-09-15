using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InterfazRest.formMessage
{
    public partial class MessageRegisterForm : Form
    {
        public MessageRegisterForm(string message)
        {
            InitializeComponent();
            txtMessage.Text = message;
        }
        

        // Método estático para mostrar el formulario como popup
        public static void Show(string message)
        {
            MessageRegisterForm messageForm = new MessageRegisterForm(message);
            messageForm.ShowDialog();
        }

        

        private void btnOk_Click_1(object sender, EventArgs e)
        {
            this.Close();
        }
    }
}
