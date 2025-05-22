# msa-service
『스프링 마이크로서비스 코딩 공작소(개정 2판)』를 기반으로, MSA 구조를 실습하며 주문/상품 서비스를 구현한 프로젝트

---
## 🏦 아키텍처
![msa drawio](https://github.com/user-attachments/assets/1e7d4eb7-441c-4c93-8563-3976bf4337c8)


---
## 📦 프로젝트 구조
```
msa-service/
├── src/
│   ├── backend/
│   │   ├── order-server/        # 주문 서비스 (Spring Boot)
│   │   └── product-server/      # 상품 서비스 (Spring Boot)
│   └── infra/
├── README.md
└── .gitignore
```
---

## 🌿 Git 브랜치 & 커밋 전략

### 🧩 브랜치 전략

| 브랜치 이름       | 용도                     |
| ------------ | ---------------------- |
| `main`       | 배포용          |
| `dev`        | 개발 통합 브랜치              |
| `be-feat/*`  | 백엔드 기능 개발 브랜치          |
| `be-fix/*`      | 버그 수정 브랜치              |
| `be-refactor/*` | 리팩토링 브랜치               |

**형식:**
```
[역할]-feat/[기능명]/[#이슈번호]
```

✅ **예시:**
```
be-feat/create-order/#1
infra-feat/gateway/#2
```

---

### 📝 커밋 메시지 컨벤션

**형식:**

```
<타입>: <작업 내용> #[이슈번호]
```

| 타입         | 설명                       |
| ---------- | ------------------------ |
| `feat`     | 새로운 기능 추가                |
| `fix`      | 버그 수정                    |
| `docs`     | 문서 수정                    |
| `style`    | 코드 스타일, 포맷 수정 (기능 변화 없음) |
| `refactor` | 리팩토링 (기능 변화 없음)          |
| `test`     | 테스트 코드 추가/수정             |
| `chore`    | 빌드 설정, 패키지 설치 등 기타 변경    |

✅ **예시:**

```
feat: order controller 생성 #1
fix: 재고 차감 로직 수정 #3
docs: 주문 서비스 README 업데이트 #2
```

---
