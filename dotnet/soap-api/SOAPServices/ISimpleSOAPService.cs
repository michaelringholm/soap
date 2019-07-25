using System.ServiceModel;

namespace soap_api
{
    [ServiceContract]
    public interface ISimpleSOAPService
    {
        [OperationContract]
        string Ping();

        [OperationContract]
        string Echo(string msg);

        [OperationContract]
        ReportDTO GetReport();             
    }
}