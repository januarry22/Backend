# Spring Security + JWT 



### 1) Spring Security 란?

    -스프링 시큐리티는 스프링 기반의 어플리케이션의 보안(인증과 권한)을 담당하는 프레임워크이다.  

### 2) JWT (Json Web Token)
    - JWT은 JSON객체를 통해 안전하게 정보를 전송할 수 있는 웹표준(RFC7519) 입니다.
    
  [더자세한설명은](https://webfirewood.tistory.com/m/115?category=694472 "Spring Security 와 JWT에 관한 설명 입니다.")


### 3) 프로젝트 적용
   
   - Spring Security 설정 파일 생성
    [참고자료](https://bezkoder.com/spring-boot-jwt-mysql-spring-security-architecture/)
   
   ![image](https://user-images.githubusercontent.com/66682208/98387965-7203ce00-2095-11eb-9979-e8c42b0e9420.png)

  
  - WebSecurity.java

  ![image](https://user-images.githubusercontent.com/66682208/98389995-2d2d6680-2098-11eb-86a7-b1265c6d8111.png)

  
  - AuthController.java
  
  ![image](https://user-images.githubusercontent.com/66682208/98391193-a8434c80-2099-11eb-9538-830e01089a3b.png)


  - Front-end(Vue.js) 로그인 요청 : Axios 방식
  
  ![image](https://user-images.githubusercontent.com/66682208/98391975-b5ad0680-209a-11eb-8d3e-a8746cb25901.png)



  - Controller => Service => ServiceImpl => Mapper => DB 접근 
    
    
    
  - UserMapper.xml
  
        <select id="readAuthorities" parameterType="String" resultType="org.springframework.security.core.authority.SimpleGrantedAuthority">
            SELECT u_auth
            FROM user_auth
            WHERE 1=1
            AND u_id = #{username}
          </select>

          <select id="readUser" parameterType="String" resultType="User">
            SELECT  user_id 					as username,
                  user_passwd					as password,	
                  user_name,
                  u_isAccountNonExpired 		as 	isAccountNonExpired,
                  u_isAccountNonLocked 		as 	isAccountNonLocked,
                    u_isCredentialNonExpired 	as 	isCredentialsNonExpired,
                    u_isEnabled 				as 	isEnabled
              FROM	User
              WHERE 1=1
              AND user_id = #{username}

          </select>

   
   - state 저장
   
   ![image](https://user-images.githubusercontent.com/66682208/98392202-058bcd80-209b-11eb-9154-83dd98f40b1a.png)
   
   
   - 새로고침시에 유저정보 state 초기화 되는 현상
      => token을 가지고 유저정보를 다시 가져와 state에 저장  
      
    
    UnpackToken({commit}){
      return new Promise((resolve,reject)=>{
        Axios.defaults.headers.common['Authorization']=`Bearer ${localStorage.getItem("token")}`
        Axios.get('http://localhost:9000/api/auth/unpackToken')
             .then(Response=>{
               console.log(Response.data)
               commit('SET_USER_REFRESH',Response.data)
             })
             .catch(Error=>{
               console.log(Error)
               console.log('unpackToken_error')
             })
      })
    },
   
   
   
   - UserMappel.xml
   
   
   <!-- 회원 정보 불러오기(새로고침) -->
        <select id="readUser_refresh" resultType="UserInfo">
              SELECT 
                ta.user_id	username ,
                ta.user_name user_name,
                tb.u_auth	auth	
              FROM user ta
                  LEFT JOIN ( 
                    SELECT ta.u_id,
                        GROUP_CONCAT(ta.u_auth SEPARATOR ",") u_auth
                    FROM 	user_auth	ta
                    GROUP BY	 ta.u_id
                        ) tb ON ta.user_id=tb.u_id
              WHERE	ta.user_id = #{username}

        </select>
    
    
    
    
    
   - state 저장
   
   
    
      ![image](https://user-images.githubusercontent.com/66682208/98393061-20ab0d00-209c-11eb-8d28-f4184c2aff4e.png)
      
      
      
      
 # Spring Boot + Restful API

    
