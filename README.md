# blocking-queue
using an intrinsic lock to create and demo a blocking queue; 

the drawback: when running prducers and cousumers in a thread pool, very soon all threads are blocked and in waiting states. There is no even a thread able to run and notify the
rest of threads. 

