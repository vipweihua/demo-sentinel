# demo-sentinel
This project is trying to simulate client service calling server service with flow control.

Just start up the ServerApplication and ClientApplication and visit the APIs defined in controller to see the flow control.


1. Visit http://localhost:8001/123 to simulate server side flow control.
2. Visit http://localhost:8002/123 to simulate client side OpenFeign flow control.
3. Visit http://localhost:8002/123/rest to simulate client side RestTemplate flow control.

Personal Opinion:
Use server side flow control as soon as possible.
If you have to use client side flow control, make **RestTemplate** as the **low** priority.