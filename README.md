🚀 고성능 SNS 알림센터
멀티 모듈 아키텍처와 Kafka, MongoDB, Redis 기반으로 구축한 확장형 알림 시스템입니다.
좋아요, 댓글, 팔로우와 같은 SNS 알림을 대규모 트래픽 환경에서도 안정적으로 처리할 수 있도록 설계했습니다.

핵심 목표는 안정성, 확장성, 실시간성입니다.


⭐ 주요 기능
• Kafka 기반 비동기 알림 생성 및 처리
• 알림 목록 조회, 읽음 처리, 신규 알림 여부 확인
• MongoDB TTL 인덱스를 통한 90일 자동 삭제
• Redis 캐시 기반 조회 속도 최적화
• 모듈별 책임 분리로 유지보수성과 확장성 강화


🧱 멀티 모듈 구성
notification-center
 ├── core            공통 도메인 모델
 ├── api             REST API 계층
 ├── consumer        Kafka 이벤트 소비자
 ├── infrastructure  MongoDB, Redis, Kafka 설정
 └── common          예외 처리 및 공통 컴포넌트


🛰 시스템 구조
Client → API 서버 → Kafka → Consumer → MongoDB / Redis

알림 생성과 조회의 책임을 분리하여
조회 흐름이 생성 트래픽의 영향을 받지 않도록 구성했습니다.

🔄 Sequence Diagram (핵심 흐름)
sequenceDiagram
Client->>API: 알림 생성 요청
API->>Kafka: 이벤트 발행
Kafka->>Consumer: 메시지 전달
Consumer->>Mongo: 알림 저장
Consumer->>Redis: 캐시 반영

Client->>API: 알림 조회
API->>Redis: 캐시 조회
Redis-->>API: 조회 결과 반환
API-->>Client: 알림 목록 응답

📚 핵심 API
• 알림 목록 조회
GET /api/notifications
최신순으로 20개 반환하며, pivot 기반 커서 페이징을 사용합니다.

• 알림 읽음 처리
POST /api/notifications/read
사용자의 마지막 읽음 시각을 저장하여 읽지 않은 알림을 구분합니다.

• 신규 알림 여부 확인
GET /api/notifications/new
마지막 읽음 시각과 최신 알림 발생 시각을 비교해 결과를 반환합니다.


🛠 기술 스택
- Language and Framework
• Java 21
• Spring Boot
• Spring Cloud Stream

- Messaging
• Kafka

- Database
• MongoDB

- Cache
• Redis

- Infrastructure
• Docker
• Docker Compose

- Testing
• Testcontainers 기반 통합 테스트


⚡ 성능 전략
• Kafka Partition으로 알림 생성 트래픽 분산
• Redis 캐싱으로 조회 지연 최소화
• MongoDB TTL 인덱스를 통한 자동 데이터 정리
• 기능별 모듈 분리로 서비스 책임 단순화

MongoDB TTL 인덱스를 통한 자동 데이터 정리

기능별 모듈 분리로 서비스 책임 단순화
