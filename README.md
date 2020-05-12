#NGINXHUNTER
#####This project provides an API service to detect input hostname server types and ip addresses matching with desired server type. 
##Before start
####CMD docker-compose build
####CMD docker-compose up
#####Note: _Application runs on port 8888 by default._
##API documentation
###POST 
#####http://localhost:8888/detectionapi/detectdomains/nginx
#####Input body : ["example.com", "blog.detectify.com", "www.wordpress.com", "s3.amazonaws.com", "demo.nginx.com", "www.turkcell.com.tr"]
#####Response :
                    { 
                    "www.wordpress.com": [
                        "192.0.78.13",
                        "192.0.78.12"
                    ],
                    "demo.nginx.com": [
                        "206.251.255.64"
                    ],
                    "blog.detectify.com": [
                        "104.196.191.243"
                    ]
                }
####Note : You can also change nginx with any server type to detect other server types.

###POST
#####http://localhost:8888/detectionapi/detectdomains/amazons3
#####Input Body : ["example.com", "blog.detectify.com", "www.wordpress.com", "s3.amazonaws.com", "demo.nginx.com", "www.turkcell.com.tr"]
#####Response :
                {
                    "s3.amazonaws.com": [
                        "52.216.96.149"
                    ]
                }
