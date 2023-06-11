# otomotiv Projesi

Otomotiv Projesi, otomotiv yedek parçalarının satılabileceği bir e-ticaret platformudur. Bu projede, admin tarafından eklenen ürünlerin güncellenebilmesi ve silinebilmesi sağlanır. Kullanıcılar kayıt olarak sisteme giriş yapabilir ve kayıt olduktan sonra e-posta doğrulaması yaparak hesabını aktif hale getirebilir. Ayrıca, beş defa hatalı giriş yapan kullanıcılar otomatik olarak engellenir ve engellemeyi sadece admin kaldırabilir. Kullanıcıların hatalı girişleri ise "project.log" dosyasında loglanır.

1. Proje dosyasını bilgisayarınıza indirin.
2. MySQL veritabanınızı hazırlayın. Örnek olarak "otomotiv" adında bir veritabanı oluşturabilirsiniz.
3. `application.properties` dosyasını açın ve aşağıdaki ayarları yapın:
    - `spring.datasource.url` için MySQL veritabanı bağlantı URL'sini belirtin. Örneğin: `jdbc:mysql://localhost:3306/otomotiv`
    - `spring.datasource.username` ve `spring.datasource.password` için MySQL veritabanı kullanıcı adı ve şifresini girin.
    - `host` için Mail adresini ve mail şifresini girin.
    - E-posta sunucusuna authenticate olabilmek için Gmail adresine sahip olmanız gerekmektedir. Gmail'in kendi ayarlarından bir uygulama şifresi almanız gerekmektedir.
    - JWT token kullanabilmek için bir `jwt_secret_key` belirleyin.
4. Uygulamayı ayağa kaldırın.

## Veritabanı Ayarları

Uygulama çalıştırıldığında, aşağıdaki adımları izleyerek "roles" tablosunu oluşturunuz:

1. Veritabanınıza bağlanın.
2. Roles tablosunu kontrol edin. Eğer roles tablosu boş ise aşağıdaki SQL sorgularını çalıştırın:

INSERT INTO roles(name) VALUES ('ROLE_USER');
INSERT INTO roles(name) VALUES ('ROLE_ADMIN');

Postman Koleksiyonu
Postman kullanarak aşağıdaki HTTP isteklerini gönderebilirsiniz. İstekleri gönderirken, isteklere ait Bearer Token'ları eklemeyi unutmayınız. Postman koleksiyonu, proje dosyasının ana dizini altında bulunan "otomotiv_postman_collection.json" dosyasında yer almaktadır.







