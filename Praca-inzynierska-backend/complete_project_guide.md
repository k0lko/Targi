# PBS Booth Booking System - Kompletny Przewodnik Projektu

## Roadmap - System Rezerwacji Stoisk na Targi Pracy PBŚ

### ETAP 1: BACKEND (Spring Boot + PostgreSQL) - 4-5 tygodni

#### Tydzień 1: Konfiguracja projektu i struktura bazy danych

**1.1 Setup projektu Spring Boot**
- Inicjalizacja projektu przez Spring Initializr
- Dodanie dependencies: Spring Web, Spring Data JPA, PostgreSQL Driver, Spring Security, Spring Boot Validation
- Konfiguracja application.properties
- Setup Docker dla PostgreSQL (opcjonalnie)

**1.2 Projektowanie i tworzenie bazy danych**
```sql
-- Tworzenie tabel w kolejności:
1. booth_types (typy stoisk A, B, Sponsor)
2. additional_services (krzesła, panele, catering)
3. companies (dane firm)
4. booths (stoiska z numeracją 1-60)
5. reservations (rezerwacje)
6. reservation_services (dodatkowe usługi w rezerwacji)
```

**1.3 Wypełnienie danych początkowych** 
- Skrypt SQL z typami stoisk i cenami z dokumentu PBŚ
- Dane 60 stoisk z mapą
- Podstawowe usługi dodatkowe

#### Tydzień 2: Modele JPA i repozytoria

**2.1 Utworzenie encji JPA**
- `Company.java` - dane firmy
- `BoothType.java` - typy stoisk  
- `Booth.java` - pojedyncze stoisko
- `AdditionalService.java` - usługi dodatkowe
- `Reservation.java` - rezerwacja
- `ReservationService.java` - tabela łącząca

**2.2 Repozytoria Spring Data JPA** 
- `CompanyRepository`
- `BoothRepository` (z query dla dostępnych stoisk)
- `ReservationRepository`
- `AdditionalServiceRepository`

**2.3 Testy jednostkowe repozytoriów**
- Testy dla każdego repozytorium
- Testy złożonych zapytań

#### Tydzień 3: Warstwa serwisowa (Business Logic)

**3.1 Service Classes**
- `CompanyService` - rejestracja, logowanie, profil
- `BoothService` - zarządzanie stoiska, sprawdzanie dostępności
- `ReservationService` - proces rezerwacji, kalkulacja cen
- `AdditionalServiceService` - zarządzanie usługami dodatkowymi

**3.2 Logika kalkulacji cen **
- Automatyczne liczenie VAT (23% dla stoisk, 8% dla cateringu)
- Walidacja dostępności stoisk
- Sprawdzanie kompatybilności usług z typami stoisk

**3.3 Testy serwisów**
- Unit testy dla logiki biznesowej
- Mock testy dla interakcji między serwisami

#### Tydzień 4: REST API Controllers

**4.1 Kontrolery API**
```java
@RestController("/api/companies") - CompanyController
@RestController("/api/booths") - BoothController  
@RestController("/api/reservations") - ReservationController
@RestController("/api/admin") - AdminController
```

**4.2 Endpointy do implementacji:**
- `GET /api/booths` - lista dostępnych stoisk
- `GET /api/booths/{id}` - szczegóły stoiska
- `POST /api/companies/register` - rejestracja firmy
- `POST /api/companies/login` - logowanie
- `POST /api/reservations` - nowa rezerwacja
- `GET /api/reservations/company/{id}` - rezerwacje firmy
- `GET /api/admin/reservations` - wszystkie rezerwacje (admin)

**4.3 Walidacja danych i error handling (2 dni)**
- @Valid annotations
- Custom validators
- Global Exception Handler
- Proper HTTP status codes

#### Tydzień 5: Security i finalizacja

**5.1 Spring Security**
- JWT authentication
- Role-based access (COMPANY, ADMIN)
- Security configuration
- Password encoding

**5.2 Testy integracyjne API**
- @SpringBootTest dla endpointów
- TestContainers dla PostgreSQL
- Mockito dla external dependencies

**5.3 Dokumentacja API**
- OpenAPI/Swagger konfiguracja
- Opisanie endpointów
- Przykłady request/response

---

### ETAP 2: FRONTEND (React + TypeScript + TailwindCSS)

#### Tydzień 1: Setup projektu i podstawowe komponenty

**1.1 Inicjalizacja projektu React TypeScript**
- `npx create-react-app frontend --template typescript`
- Instalacja: TailwindCSS, React Router, Axios, React Hook Form
- Konfiguracja TailwindCSS i TypeScript
- Podstawowa struktura folderów

**1.2 Routing i layout**
```typescript
/                 - Strona główna
/login           - Logowanie firmy
/register        - Rejestracja firmy  
/dashboard       - Panel firmy
/booking         - Mapa i rezerwacja
/admin           - Panel administratora
```

**1.3 Podstawowe komponenty UI**
- `Header.tsx` - nawigacja
- `Footer.tsx` 
- `Button.tsx` - reusable button component
- `Modal.tsx` - dla popup'ów
- `LoadingSpinner.tsx`
- `ErrorMessage.tsx`

#### Tydzień 2: TypeScript typy i uwierzytelnianie

**2.1 Definicje TypeScript**
- `auth.types.ts` - typy dla uwierzytelniania
- `booth.types.ts` - typy stoisk i usług
- `reservation.types.ts` - typy rezerwacji
- `api.types.ts` - typy API responses

**2.2 Context API dla stanu globalnego**
- `AuthContext.tsx` - stan logowania
- `BookingContext.tsx` - stan rezerwacji
- Custom hooks: `useAuth.ts`, `useBooking.ts`

**2.3 Komponenty uwierzytelniania**
- `LoginForm.tsx` - formularz logowania
- `RegisterForm.tsx` - rejestracja firmy (zgodny z formularzem PBŚ)
- `ProtectedRoute.tsx` - ochrona tras
- Integracja z backend API

#### Tydzień 3: Interaktywna mapa stoisk (najważniejszy komponent)

**3.1 Komponent InteractiveMap.tsx**
- SVG-based mapa oparta na układzie z PDF
- 60 stoisk w układzie zgodnym z dokumentem
- Color coding: zielone (dostępne), czerwone (zajęte), niebieskie (wybrane)
- Hover effects z podstawowymi informacjami
- Click handling dla selekcji stoiska
- TypeScript interfaces dla map data

**3.2 Panel informacji o stoisku**
- `BoothInfoPanel.tsx` 
- Wyświetlanie: numer, typ, wymiary, cena bazowa
- Lista dostępnych usług dodatkowych
- Przycisk "Zarezerwuj"

**3.3 Responsywność mapy**
- Zoom i pan functionality (opcjonalnie)
- Mobile-friendly design
- Touch events dla urządzeń mobilnych

#### Tydzień 4: Formularz rezerwacji i kalkulator

**4.1 Formularz rezerwacji**
- `BookingForm.tsx` - główny formularz z TypeScript
- Wybór usług dodatkowych z checkboxami
- Walidacja zgodna z regułami biznesowymi
- Integration z React Hook Form + TypeScript

**4.2 Kalkulator cen**
- `PriceCalculator.tsx`
- Real-time kalkulacja podczas wyboru usług
- Wyświetlanie VAT osobno (23% i 8%)
- Podsumowanie końcowe
- TypeScript interfaces dla price calculation

**4.3 Potwierdzenie rezerwacji**
- Modal z podsumowaniem
- Generowanie "faktury" (mock)
- Email confirmation (mock)

#### Tydzień 5: Panel administracyjny i finalizacja

**5.1 Admin Dashboard**
- `AdminDashboard.tsx`
- Lista wszystkich rezerwacji
- Filtry: po dacie, firmie, typie stoiska
- Statystyki: zajętość, przychody
- Export do CSV/PDF (opcjonalnie)

**5.2 Panel firmy**
- `CompanyDashboard.tsx`
- Historia rezerwacji firmy
- Edycja danych firmy
- Status płatności (mock)

**5.3 Polish i UX improvements**
- Loading states podczas API calls
- Error handling i user feedback
- Smooth animations z TailwindCSS
- Final testing i bug fixes

---

### ETAP 3: INTEGRACJA I TESTING 

#### Integration Testing
- End-to-end testy całego flow rezerwacji
- Testing na różnych urządzeniach i przeglądarkach
- Performance testing

#### Deployment Preparation  
- Environment configuration
- Database migration scripts
- Build optimization
- Documentation finalna

---

## HARMONOGRAM CAŁKOWITY:

 Backend (Spring Boot + PostgreSQL)<br>
 Frontend (React + TypeScript + TailwindCSS) <br> 
Integracja, testy, deployment<br>

## KLUCZOWE MILESTONE'Y:
1. Działające REST API z bazą danych
2. Interaktywna mapa z rezerwacją stoisk
3. Kompletny proces rezerwacji z kalkulatorem
4. Panel administracyjny
