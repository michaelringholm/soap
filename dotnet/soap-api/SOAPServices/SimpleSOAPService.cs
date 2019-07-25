using System;

namespace soap_api
{
    public class SimpleSOAPService : ISimpleSOAPService
    {
        public string Echo(string msg)
        {
            return msg;
        }

        public string Ping()
        {
            return "Ping!";
        }

        public ReportDTO GetReport()
        {
            return new ReportDTO{ ReportID = 1, Title = "Financial Report", LastUpdated = DateTime.Now };
        }        
    }
}