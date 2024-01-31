# 13-Swagger-Front-Back 연동

## 설정 
### BoardApplication에 CORS 처리 추가
아래 코드로 CORS 처리 추가
>BoardApplication.java
```
	@Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins("http://localhost");
            }
        };
    }
```