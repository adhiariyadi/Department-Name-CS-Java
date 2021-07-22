<h1 align="center">Selamat datang di Department Name! 👋</h1>

## Apa itu Department Name?

Aplikasi Department Name yang dibuat oleh <a href="https://github.com/adhiariyadi"> Adhi Ariyadi </a>. **Department Name adalah aplikasi laundry berbasis desktop yang di buat menggunakan java, C#, SQL SERVER**

## Fitur apa saja yang tersedia di Department Name?

- Autentikasi
- Employee & CRUD
- Package & CRUD
- Service & CRUD
- Transaksi
- View Transaksi
- Dan lain-lain

## Release Date

**Release date : 22 Jul 2021**

> Department Name merupakan project open source yang dibuat oleh Adhi Ariyadi. Kalian dapat download/fork/clone. Cukup beri stars di project ini agar memberiku semangat. Terima kasih!

---

## Default Account for testing

**Admin Default Account**

- username: admin
- Password: admin123

---

## Install

1. **Clone Repository**

```bash
git clone https://github.com/adhiariyadi/Department-Name-Java-CS.git
```

2. **Import database `DBDepartment.sql` menggunakan SQLServer**

3. **Ubah koneksi database `Server/Web.config`**

```bash
<connectionStrings>
  <add name="DepartmentDB" connectionString="Data Source={server name};Initial Catalog={name database};Integrated Security=True" providerName="System.Data.SqlClient" />
</connectionStrings>
```

4. **Jalankan `Server` menggunakan Visual Studio**

5. **Ubah url api `Client/app/src/main/java/com/department/client/api/ApiClient.java`**

```bash
private static final String BASE_URL = "{url api}";
```

6. **Setting ipconfig `Client/app/src/main/res/xml/network_security_config.xml`**

```bash
<?xml version="1.0" encoding="utf-8"?>
<network-security-config>
    <domain-config cleartextTrafficPermitted="true">
        <domain includeSubdomains="true">{ ipconfig }</domain>
    </domain-config>
</network-security-config>
```

7. **Jalankan `Client` menggunakan Android Studio**

## Author

- Facebook : <a href="https://web.facebook.com/adhiariyadi.me/"> Adhi Ariyadi</a>
- LinkedIn : <a href="https://www.linkedin.com/in/adhiariyadi/"> Adhi Ariyadi</a>

## Contributing

Contributions, issues and feature requests di persilahkan.
Jangan ragu untuk memeriksa halaman masalah jika Anda ingin berkontribusi. **Berhubung Project ini saya sudah selesaikan sendiri, namun banyak fitur yang kalian dapat tambahkan silahkan berkontribusi yaa!**

## License

- Copyright © 2021 Adhi Ariyadi.
- **Department Name is open-sourced software licensed under the MIT license.**
