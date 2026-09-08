
## 1. Phân Tích Lỗi và Nguyên Nhân
Trong bài tập này, ứng dụng Config Server gặp phải các vấn đề cốt lõi sau:
- **Thiếu Annotation kích hoạt:** Thiếu `@EnableConfigServer` trên lớp `ConfigServerApplication`, khiến Spring Boot chỉ chạy như một ứng dụng web thông thường thay vì một Config Server.
- **Lỗi cấu hình Git URI:** Đường dẫn `uri` trong file `application.yml` thiếu đuôi `.git`, làm cho thư viện ngầm JGit không thể nhận diện và clone/fetch repository.
- **Lỗi tên nhánh (Branch):** Cấu hình `default-label` trỏ nhầm vào `master` trong khi kho lưu trữ thực tế sử dụng nhánh `main`.

## 2. Luồng Xử Lý Request
Khi một microservice (ví dụ: `restaurant-service`) gửi yêu cầu lấy cấu hình:
1. **Tiếp nhận:** Request dạng `GET /restaurant-service/prod` được gửi đến Config Server (chạy ở port `8888`).
2. **Đồng bộ Git:** Config Server sử dụng JGit để kết nối tới Git repository thông qua `uri` và nhánh `default-label: main`.
3. **Tìm kiếm file:** Hệ thống tìm kiếm các file cấu hình khớp mẫu tên (`restaurant-service-prod.yml`, `restaurant-service.yml`, v.v.).
4. **Phản hồi:** Trả về một đối tượng `Environment` chứa toàn bộ cấu hình dưới dạng JSON cho service gọi đến.

## 3. Cách Kiểm Thử Độc Lập
Trước khi tích hợp vào toàn bộ hệ thống Microservice, bạn có thể kiểm tra Config Server:
- **Truy cập trực tiếp qua trình duyệt/Postman:** `http://localhost:8888/restaurant-service/prod`
- **Kết quả kỳ vọng:** Nhận được dữ liệu JSON hiển thị đầy đủ tên ứng dụng, profile, label và các thuộc tính cấu hình từ kho lưu trữ Git.

