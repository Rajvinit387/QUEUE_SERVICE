package com.example;



import redis.clients.jedis.Jedis;

    public class upstashQueue {

        private final Jedis jedis;
        private final String queueKey="redis_queue";

        public upstashQueue(String host, int port, String password) {

            this.jedis = new Jedis(host,port);

            jedis.auth(password);
        }

        // Enqueue an item to the queue
        public void enqueue(String item) {
            jedis.rpush(queueKey, item);
        }

        // Dequeue an item from the queue
        public String dequeue() {
            return jedis.lpop(queueKey);
        }

        // Get the size of the queue
        public long size() {
            return jedis.llen(queueKey);
        }

        // Check if the queue is empty
        public boolean isEmpty() {
            return size() == 0;
        }
    }


