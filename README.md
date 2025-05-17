# 📦 Customer Management API - Spring Boot Backend
Bu proje, müşterileri listeleme, arama, ekleme, silme ve güncelleme işlemleri için geliştirilen bir RESTful Spring Boot uygulamasıdır. H2 bellek içi veritabanı kullanır ve Swagger UI ile test edilebilir.

---

##  🧰 Kullanılan Teknolojiler

- Java 17  
- Spring Boot 3  
- Spring Data JPA  
- H2 In-Memory Database  
- Springdoc OpenAPI (Swagger)  
- Maven  

---
## Kurulum

### Gereksinimler

- Java 17+
- Maven

---

### Derleme
mvn clean install
mvn spring-boot:run

---
## API Bilgileri
POST /customers
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "annualSpend": 2000,
  "lastPurchaseDate": "2025-05-10"
}
```
GET /customers/1
```
GET /customers?email=jane.doe@example.com
```
PUT /customers/1
Content-Type: application/json

{
  "name": "Jane D.",
  "email": "jane.d@example.com",
  "annualSpend": 2500,
  "lastPurchaseDate": "2025-05-15"
}
```
DELETE /customers/1

---
## 🔗 API URL

Uygulama başlatıldığında aşağıdaki adres üzerinden API erişilebilir:

- `http://localhost:8080`

---
### Swagger UI
API'yi test etmek ve belgelerine ulaşmak için Swagger UI kullanılabilir:

- `http://localhost:8080/swagger-ui/index.html`
  
Swagger arayüzü üzerinden tüm uç noktalara istek gönderebilir ve yanıtları görebilirsiniz.

---
### H2 Console
Uygulama çalışırken veritabanına erişmek için aşağıdaki H2 konsolu kullanılabilir:

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Kullanıcı Adı:** `sa`
- **Şifre:** `password`
---

## 👥 Geliştirici Ekibi

| İsim                    | Rol           | GitHub                                                         |
|-------------------------|---------------|----------------------------------------------------------------|
| Hüsnü Selman GÜMÜŞSOY   | Full Stack    | [github.com/selmangumussoy](https://github.com/selmangumussoy) |
| Berk Muhammed DOĞAN     | Full Stack    | [github.com/yusuf-ozbay](https://github.com/yusuf-ozbay)       |
| Yusuf ÖZBAY             | Full Stack    | [github.com/Berkmdogan](https://github.com/Berkmdogan)         |

## 🖼️ Ekran Görüntüleri

- Swagger Arayüzü:  
  ![Swagger UI](./screenshots/swagger.png)

- H2 Console:  
  ![H2 Console](./screenshots/h2-console.png)

- Müşteri Ekleme Formu:  
  ![Add Customer](./screenshots/add-customer.png)

- Müşteri Listesi:  
  ![Customer List](./screenshots/customer-list.png)

## 💡 Varsayımlar

- Her müşterinin e-posta adresi benzersizdir.
- `name`, `email`, `lastPurchaseDate` alanları zorunludur.
- `annualSpend` sayısal bir değerdir ve negatif olamaz.
- Tarih formatı: `yyyy-MM-dd` olmalıdır.
- Veritabanı H2 olduğundan dolayı uygulama kapanınca veriler sıfırlanır.
- H2 Console ve Swagger UI sadece geliştirme ortamında açıktır.


