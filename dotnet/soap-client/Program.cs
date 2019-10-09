using System;
using System.ServiceModel;

namespace soap_client
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Hello World!");
            CallSOAPEndpoint();
        }

        private static void CallSOAPEndpoint()
        {
            var binding = new BasicHttpsBinding();
            var endpoint = new EndpointAddress(new Uri("<url>"));
            dynamic channelFactory = new ChannelFactory<ISimpleSOAPService>(binding, endpoint);
            var serviceClient = channelFactory.CreateChannel();
            var result = serviceClient.Ping();
            channelFactory.Close();
        }
    }
}
