using System;
using System.Runtime.Serialization;

namespace soap_api
{
    [DataContract]
    public class ReportDTO
    {
        [DataMember] public DateTime LastUpdated { get; internal set; }
        [DataMember] public string Title { get; internal set; }
        [DataMember] public int ReportID { get; internal set; }
    }
}