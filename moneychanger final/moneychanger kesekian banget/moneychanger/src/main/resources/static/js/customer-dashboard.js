// Fungsi untuk menampilkan informasi metode pembayaran
function togglePaymentFields() {
    const paymentMethod = document.getElementById("paymentMethod").value; // Mendapatkan nilai metode pembayaran
    const cashInfo = document.getElementById("cashInfo"); // Mendapatkan elemen informasi pembayaran cash
    const ewalletInfo = document.getElementById("ewalletInfo"); // Mendapatkan elemen informasi pembayaran e-wallet
    const ewalletNumber = document.getElementById("ewalletNumber"); // Mendapatkan elemen nomor rekening e-wallet

    // Cek apakah metode pembayaran yang dipilih adalah cash
    if (paymentMethod === "cash") {
        cashInfo.style.display = "block"; // Tampilkan informasi cash
        ewalletInfo.style.display = "none"; // Sembunyikan informasi e-wallet
        ewalletNumber.required = false; // Nomor rekening tidak wajib untuk metode Cash
    } else if (paymentMethod === "ewallet") { // Cek jika metode pembayaran adalah e-wallet
        cashInfo.style.display = "none"; // Sembunyikan informasi cash
        ewalletInfo.style.display = "block"; // Tampilkan informasi e-wallet
        ewalletNumber.required = true; // Nomor rekening wajib untuk metode E-Wallet
    } else { // Jika tidak ada metode pembayaran yang dipilih
        cashInfo.style.display = "none"; // Sembunyikan informasi cash
        ewalletInfo.style.display = "none"; // Sembunyikan informasi e-wallet
        ewalletNumber.required = false; // Default, nomor rekening tidak wajib
    }
}

// Fungsi untuk menampilkan nominal setelah konversi
function showConvertedAmount() {
    const amount = parseFloat(document.getElementById("amount").value); // Ambil jumlah nominal yang dimasukkan
    const transactionType = document.getElementById("transactionType").value; // Ambil tipe transaksi (beli/jual)
    const sourceCurrency = document.getElementById("sourceCurrency").value; // Ambil mata uang asal
    const targetCurrency = document.getElementById("targetCurrency").value; // Ambil mata uang tujuan

    // Ambil kurs dari tabel
    const currencyRates = {}; // Membuat objek untuk menyimpan data kurs
    const rows = document.querySelectorAll("tbody tr"); // Mendapatkan semua baris di tabel kurs
    rows.forEach(row => {
        const mataUang = row.cells[0].innerText.trim(); // Ambil nama mata uang
        const beli = parseFloat(row.cells[1].innerText); // Ambil kurs beli
        const jual = parseFloat(row.cells[2].innerText); // Ambil kurs jual
        currencyRates[mataUang] = { beli, jual }; // Simpan kurs mata uang
    });

    // Validasi input nominal
    if (!amount || isNaN(amount) || amount <= 0) {
        alert("Masukkan jumlah nominal yang valid (lebih dari 0)."); // Tampilkan pesan jika input tidak valid
        return;
    }
    if (!sourceCurrency || !targetCurrency) {
        alert("Pilih mata uang asal dan tujuan."); // Pesan jika mata uang asal atau tujuan belum dipilih
        return;
    }
    if (sourceCurrency === targetCurrency) {
        alert("Mata uang asal dan tujuan tidak boleh sama."); // Pesan jika mata uang asal dan tujuan sama
        return;
    }
    if (!transactionType) {
        alert("Pilih jenis transaksi (beli/jual)."); // Pesan jika tipe transaksi belum dipilih
        return;
    }

    // Ambil kurs mata uang asal dan tujuan
    const sourceRate = currencyRates[sourceCurrency]; // Ambil kurs mata uang asal
    const targetRate = currencyRates[targetCurrency]; // Ambil kurs mata uang tujuan
    if (!sourceRate || !targetRate) {
        alert("Kurs untuk mata uang asal atau tujuan tidak ditemukan."); // Pesan jika kurs tidak ditemukan
        return;
    }

    // Hitung nominal setelah konversi berdasarkan tipe transaksi
    let convertedAmount;
    if (transactionType === "beli") { // Jika transaksi beli
        convertedAmount = (amount * sourceRate.jual) / targetRate.jual; // Hitung konversi beli
    } else if (transactionType === "jual") { // Jika transaksi jual
        convertedAmount = (amount * sourceRate.beli) / targetRate.jual; // Hitung konversi jual
    }

    // Tampilkan hasil konversi pada elemen
    document.getElementById("convertedAmount").innerText =
        `${convertedAmount.toFixed(2)} ${targetCurrency}`; // Menampilkan hasil konversi dengan format dua desimal
}