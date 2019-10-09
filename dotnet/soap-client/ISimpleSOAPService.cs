using System.ServiceModel;

namespace soap_client
{
    [ServiceContract]
    internal interface ISimpleSOAPService
    {
        [OperationContract]
        string Ping();

        [OperationContract]
        string Echo(string msg);
    }
}