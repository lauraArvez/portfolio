using InterfazRest.Services;
using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace InterfazRest
{
    public partial class TrainerForm : Form
    {
        // Servicio de autenticación inyectado
        private readonly IAuthService _authService;

        public TrainerForm(IAuthService authService)
        {
            InitializeComponent();
            _authService = authService;
        }
    }
}
