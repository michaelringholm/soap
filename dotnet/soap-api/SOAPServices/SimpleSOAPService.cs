namespace soap_api
{
    internal class SimpleSOAPService : ISimpleSOAPService
    {
        public string Echo(string msg)
        {
            return msg;
        }

        public string Ping()
        {
            return "Ping!";
        }
    }
}