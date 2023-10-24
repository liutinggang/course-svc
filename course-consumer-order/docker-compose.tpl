version: "3.6"
services:
  consumer-order-server:
    image: IMAGE_NAME
    container_name: consumer-order-server
    hostname: consumer-order-server
    network_mode: host
    volumes:
      - /home/course/data:/volume:rw
    environment:
      PROFILE: prod
    restart: always