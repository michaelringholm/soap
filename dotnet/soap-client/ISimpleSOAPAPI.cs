using System.ServiceModel;

namespace soap_client
{
    [ServiceContract]
    internal interface ISimpleSOAPAPI
    {
        [OperationContract]
        string Ping();

        [OperationContract]
        string Echo(string msg);
    }
}