using System;

namespace soap_api
{
    public class ReportDTO
    {
        public DateTime LastUpdated { get; internal set; }
        public string Title { get; internal set; }
        public int ReportID { get; internal set; }
    }
}