using System.Drawing;

namespace InterfazRest
{
    public static class ThemeColor
    {
        // Definición de colores para el tema "Atleta"
        public static class Atleta
        {
            public static Color DefaultColor = Color.FromArgb(16, 11, 120);
            public static Color ActiveColor = Color.FromArgb(80, 160, 202);
            public static Color Color1 = Color.FromArgb(255, 255, 0); // Amarillo fosforescente 
            public static Color Color2 = Color.FromArgb(80, 160, 202); // Azul claro
            public static Color Color3 = Color.FromArgb(251, 221, 0); // Gold
            public static Color Color4 = Color.FromArgb(80, 160, 202); // Amarillo claro
        }

        // Definición de colores para el tema "Club"
        public static class Club
        {
            public static Color Color1 = Color.FromArgb(121, 47, 18);   // color base principal
            public static Color Color2 = Color.FromArgb(255, 255, 255); // Blanco
            public static Color Color3 = Color.FromArgb(144, 89, 67);   // color opaco
            public static Color Color4 = Color.White;
            public static Color Color5 = Color.FromArgb(255, 255, 128); // Amarillo claro 
            public static Color Color6 = Color.FromArgb(255, 192, 192); // Rosa claro
        }
    }
}
