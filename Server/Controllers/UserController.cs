using Server.Models;
using System;
using System.Collections.Generic;
using System.Configuration;
using System.Data;
using System.Data.SqlClient;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Web.Http;

namespace Server.Controllers
{
    public class UserController : ApiController
    {
        [Route("api/Login")]
        [HttpPost]
        public HttpResponseMessage Login(User req)
        {
            try
            {
                string query = @"select * from [user] where username='" + req.Username + @"' and password='" + req.Password + @"'";
                DataTable table = new DataTable();
                using (var conn = new SqlConnection(ConfigurationManager.ConnectionStrings["DepartmentDB"].ConnectionString))
                using (var cmd = new SqlCommand(query, conn))
                using (var da = new SqlDataAdapter(cmd))
                {
                    cmd.CommandType = CommandType.Text;
                    da.Fill(table);
                }

                var result = new { success = true, message = "Login Success!", data = table };
                return Request.CreateResponse(HttpStatusCode.OK, result);
            }
            catch (Exception error)
            {
                var result = new { success = false, message = error.Message };
                return Request.CreateResponse(HttpStatusCode.Unauthorized, result);
                throw;
            }
        }

        [Route("api/Register")]
        [HttpPost]
        public HttpResponseMessage Register(User req)
        {
            try
            {
                string query = @"insert into [user] values('" + req.FullName + @"', '" + req.Username + @"', '" + req.Password + @"')";
                DataTable table = new DataTable();
                using (var conn = new SqlConnection(ConfigurationManager.ConnectionStrings["DepartmentDB"].ConnectionString))
                using (var cmd = new SqlCommand(query, conn))
                using (var da = new SqlDataAdapter(cmd))
                {
                    cmd.CommandType = CommandType.Text;
                    da.Fill(table);
                }

                var result = new { success = true, message = "Register Successfuly!" };
                return Request.CreateResponse(HttpStatusCode.OK, result);
            }
            catch (Exception err)
            {
                var result = new { success = false, message = err.Message };
                return Request.CreateResponse(HttpStatusCode.InternalServerError, result);
            }
        }
    }
}
