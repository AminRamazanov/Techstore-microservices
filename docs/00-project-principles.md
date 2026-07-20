## Purpose

Bu sənəd **AlSat Marketplace** layihəsinin əsas inkişaf prinsiplərini müəyyən edir.

Layihənin məqsədi yalnız işləyən bir tətbiq hazırlamaq deyil, **real istifadəçiləri qəbul edə biləcək, genişlənə bilən və production səviyyəli marketplace platformasının texniki təməlini qurmaqdır**.

Bu sənəddə qeyd olunan prinsiplər layihədə qəbul ediləcək bütün memarlıq və kod qərarları üçün əsas qayda hesab olunur.

---

## Project Vision

AlSat istifadəçilərin məhsul və xidmət elanları yerləşdirə, axtara, müqayisə edə və satıcı ilə əlaqə saxlaya bildiyi müasir marketplace platformasıdır.

Platforma özü məhsul satmır; əsas məqsəd **alıcı ilə satıcını təhlükəsiz və rahat şəkildə birləşdirməkdir**.

Uzunmüddətli hədəf:

* yüksək performanslı,
* mikroservis əsaslı,
* event-driven,
* cloud-native,
* milyonlarla istifadəçiyə xidmət göstərə biləcək platforma qurmaqdır.

---

## Core Engineering Principles

### Business First

Hər texniki qərardan əvvəl biznes problemi başa düşülməlidir.

Sual:

* Bu istifadəçiyə hansı dəyəri verir?
* Platformanın hansı problemini həll edir?

Kod biznes məqsədinin nəticəsidir.

---

### Domain First

Əvvəl **domen modeli**, sonra **database**, daha sonra **kod** yazılır.

Ardıcıllıq:

1. Business
2. Domain
3. Architecture
4. Database
5. Code

Bu yanaşma yanlış entity və servis sərhədlərinin qarşısını alır.

---

### KISS (Keep It Simple)

İlk versiya mümkün qədər sadə olmalıdır.

MVP-də yalnız istifadəçiyə dəyər verən funksiyalar hazırlanır.

Artıq mürəkkəblik yalnız real ehtiyac yarandıqda əlavə edilir.

---

### YAGNI (You Aren’t Gonna Need It)

Bu gün lazım olmayan funksionallıq əvvəlcədən implementasiya edilmir.

Məsələn:

* AI recommendation
* Elasticsearch
* Distributed cache
* Advanced analytics

Bu imkanlar gələcək üçün planlaşdırılır, lakin MVP-də yazılmır.

---

### DRY (Don’t Repeat Yourself)

Eyni biznes qaydası və ya məntiq bir neçə yerdə təkrarlanmamalıdır.

Təkrarlanan kod:

* bug riskini artırır,
* dəyişiklik xərclərini yüksəldir,
* test etməni çətinləşdirir.

---

### SOLID

Xüsusilə aşağıdakılar ciddi şəkildə tətbiq olunacaq:

* **Single Responsibility** — hər class yalnız bir məsuliyyət daşıyır.
* **Open/Closed** — yeni imkanlar əlavə edilə bilir, mövcud kod minimum dəyişir.
* **Dependency Inversion** — yüksək səviyyəli komponentlər konkret implementasiyalardan asılı olmur.

---

## Clean Architecture

Layihə qatlara bölünəcək:

* Domain
* Application
* Infrastructure
* Interface (API)

Əsas qayda:

> Domain heç bir framework-dən asılı olmamalıdır.

Bu səbəbdən biznes məntiqi:

* Spring annotations,
* HTTP,
* RabbitMQ,
* PostgreSQL

haqqında məlumatlı olmamalıdır.

---

## Domain-Driven Design (DDD)

AlSat aşağıdakı bounded context-lərə bölünür:

* Identity
* Listing
* Category
* Media
* Favorite
* Search (future)
* Chat (future)
* Notification (future)
* Promotion (future)
* Payment (future)
* Analytics (future)

Hər context:

* öz modelinə,
* öz qaydalarına,
* öz verilənlərinə,
* öz inkişaf sürətinə malikdir.

---

## Event-Driven Architecture

Servislər mümkün qədər bir-birindən **loosely coupled** olacaq.

Əsas kommunikasiya üsulları:

* **REST** — dərhal cavab tələb olunan əməliyyatlar.
* **RabbitMQ** — asinxron və eventual consistency tələb edən əməliyyatlar.

Məsələn:

* ListingCreated
* ListingPublished
* MediaUploaded
* FavoriteAdded
* PaymentCompleted (future)

---

## Reliability Principles

Məlumat itkisini minimuma endirmək üçün aşağıdakı pattern-lər istifadə olunacaq:

* Transactional Outbox
* Inbox Pattern
* Publisher Confirms
* Manual ACK/NACK
* Retry Queue
* Dead Letter Queue (DLQ)
* Idempotent Consumers

Bu pattern-lər production mühitində mesaj etibarlılığını təmin edir.

---

## Concurrency Principles

Paralel əməliyyatlarda məlumat ziddiyyətlərinin qarşısını almaq üçün:

* Optimistic Locking (`@Version`)
* `FOR UPDATE SKIP LOCKED`
* Proper Transaction Boundaries
* Idempotent Commands

istifadə olunacaq.

---

## API Design Principles

REST API-lər aşağıdakı qaydalara uyğun olacaq:

* resurs əsaslı endpoint-lər,
* düzgün HTTP metodları,
* standart status kodları,
* vahid error response formatı,
* pagination,
* filtering,
* sorting.

Məsələn:

* `POST /listings`
* `GET /listings`
* `GET /listings/{id}`
* `PATCH /listings/{id}`
* `DELETE /listings/{id}`

---

## Security Principles

İlk versiyada:

* JWT Authentication
* Role-based Authorization
* Request Validation
* Password Hashing
* CORS Configuration

Daha sonra:

* Refresh Tokens
* Rate Limiting
* Device Sessions
* Email Verification
* Two-Factor Authentication

əlavə ediləcək.

---

## Documentation First

Hər vacib qərar sənədləşdiriləcək.

Bunun üçün ayrıca **Decision Log (ADR)** saxlanılacaq.

Məsələn:

* Niyə Product yox, Listing?
* Niyə Media ayrıca servisdir?
* Niyə sellerName Listing-də saxlanılmır?
* Niyə Event-Driven yanaşma seçildi?

Bu sənədlər gələcəkdə həm inkişaf, həm də onboarding prosesini asanlaşdıracaq.

---

## Production Mindset

Hər yeni feature əlavə edilərkən aşağıdakı sual veriləcək:

> Bu qərarı sistem 1 milyon istifadəçiyə xidmət göstərsəydi də seçərdikmi?

Əgər cavab **bəli**dirsə, qərar qəbul edilir.

---

## Final Principle

AlSat layihəsinin əsas məqsədi yalnız texnologiyaları istifadə etmək deyil, **real marketplace platformasının memarlığını düzgün şəkildə qurmağı öyrənməkdir**.

Bu layihədə hər kod parçası aşağıdakı suallara cavab verməlidir:

* Bu nə problemi həll edir?
* Niyə məhz belə dizayn olunub?
* Alternativlər nə idi?
* Gələcəkdə necə genişlənəcək?
* Bu qərar sistemi daha dayanıqlı və idarəolunan edirmi?

Bu prinsiplər AlSat layihəsinin bütün gələcək inkişaf mərhələləri üçün əsas texniki və memarlıq istiqamətini müəyyən edir.
