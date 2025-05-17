# 📦 Customer Management API - Spring Boot Backend

Bu proje, müşterileri listeleme, arama, ekleme, silme ve güncelleme işlemleri için geliştirilen bir RESTful Spring Boot uygulamasıdır. H2 bellek içi veritabanı kullanır ve Swagger UI ile test edilebilir.

---

## 🧰 Kullanılan Teknolojiler

- Java 17
- Spring Boot 3
- Spring Data JPA
- H2 In-Memory Database
- Springdoc OpenAPI (Swagger)
- Maven

---

## 🚀 Uygulamayı Başlatma

### Gereksinimler

- Java 17+
- Maven

### Derleme ve Çalıştırma

```bash
mvn clean install
mvn spring-boot:run
---

## 🔗 API URL

Uygulama başlatıldığında aşağıdaki adres üzerinden API erişilebilir:

- `http://localhost:8080`

---

## 📄 Swagger UI

API'yi test etmek ve belgelerine ulaşmak için Swagger UI kullanılabilir:

- `http://localhost:8080/swagger-ui/index.html`

Swagger arayüzü üzerinden tüm uç noktalara istek gönderebilir ve yanıtları görebilirsiniz.

---

## 🗃️ H2 Console Bilgileri

Uygulama çalışırken veritabanına erişmek için aşağıdaki H2 konsolu kullanılabilir:

- **URL:** `http://localhost:8080/h2-console`
- **JDBC URL:** `jdbc:h2:mem:testdb`
- **Kullanıcı Adı:** `sa`
- **Şifre:** _(boş bırakılmalı)_

> Not: Uygulama kapatıldığında tüm veriler silinir çünkü H2 veritabanı bellek tabanlıdır.

---
---

## 📡 Örnek Endpoint İstekleri

Aşağıda uygulamadaki her bir API endpoint'ine ait örnek istekler (HTTP methodu, endpoint yolu, varsa JSON gövdesi) listelenmiştir.

---

### 🔹 1. Yeni Müşteri Oluştur (POST)

```http
POST /customers
Content-Type: application/json

{
  "name": "Jane Doe",
  "email": "jane.doe@example.com",
  "annualSpend": 2000,
  "lastPurchaseDate": "2025-05-10"
}

GET /customers/1

GET /customers?email=jane.doe@example.com

PUT /customers/1
Content-Type: application/json

{
  "name": "Jane D.",
  "email": "jane.d@example.com",
  "annualSpend": 2500,
  "lastPurchaseDate": "2025-05-15"
}

DELETE /customers/1



