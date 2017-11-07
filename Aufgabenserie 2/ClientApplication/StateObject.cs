using System.Net.Sockets;
using System.Text;

namespace ClientApplication
{
    class StateObject
    {
        // Client socket.  
        public Socket workSocket = null;

        // Receive buffer. 
        public const int BufferSize = 256;
        public byte[] buffer = new byte[BufferSize];

        // Received data string.  
        public StringBuilder sb = new StringBuilder();
    }
}
