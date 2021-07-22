using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace Server.Models
{
    public class User
    {
        public int UserId { get; set; }
        public String FullName { get; set; }
        public String Username { get; set; }
        public String Password { get; set; }
    }
}