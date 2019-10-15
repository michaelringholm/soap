using System;
using System.Net.Http;
using System.ServiceModel;
using System.Text;
using System.Threading.Tasks;
using Microsoft.Extensions.Configuration;
using Microsoft.Extensions.DependencyInjection;

namespace soap_client
{
    class Program
    {
        static void Main(string[] args)
        {
            Console.WriteLine("Starting SOAP client sample...");
            var env = Environment.GetEnvironmentVariable("ASPNETCORE_ENVIRONMENT");
            var builder = new ConfigurationBuilder().AddJsonFile($"appsettings.{env}.json", optional: false, reloadOnChange: true);
            var configuration = builder.Build();
            var soapWSDLURL = configuration.GetValue<string>("SOAPWSDLURL");
            var serviceCollection = new ServiceCollection();

            CallSOAPViaStubs(soapWSDLURL);            
            CallSOAPEndpointWithString(soapWSDLURL).ConfigureAwait(false).GetAwaiter().GetResult();
        }

        private static async Task CallSOAPEndpointWithString(string soapWSDLURL)
        {
            var httpClient = new HttpClient();
            var xml = "<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ss=\"http://tempuri.org/\">" +
            "<soapenv:Body>" +
            "<ss:Echo>" +
                "<ss:msg>Please echo this</ss:msg>" +
            "</ss:Echo>" +
            "</soapenv:Body>" +
            "</soapenv:Envelope>";
            var httpContent = new StringContent(xml, Encoding.UTF8, "application/xml");

            var result = await httpClient.PostAsync(soapWSDLURL, httpContent);
            var content = await result.Content.ReadAsStringAsync();
            Console.WriteLine(result.StatusCode);
        }

        private static void CallSOAPViaStubs(string soapWSDLURL)
        {
            var binding = new BasicHttpsBinding();
            var endpoint = new EndpointAddress(new Uri(soapWSDLURL));
            dynamic channelFactory = new ChannelFactory<ISimpleSOAPService>(binding, endpoint);
            var serviceClient = channelFactory.CreateChannel();
            var result = serviceClient.Ping();
            channelFactory.Close();
        }
    }
}
