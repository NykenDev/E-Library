package com.gabniel.elibrary.screen.auth.login

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabniel.elibrary.database.dao.AccountDao
import com.gabniel.elibrary.database.dao.EbookDao
import com.gabniel.elibrary.database.entities.EbookEntity
import com.gabniel.elibrary.databinding.ActivityLoginBinding
import com.gabniel.elibrary.screen.auth.register.RegisterActivity
import com.gabniel.elibrary.screen.main.MainActivity
import com.gabniel.elibrary.utils.AppManager
import com.gabniel.elibrary.utils.showToast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var accountDao: AccountDao

    @Inject
    lateinit var ebookDao: EbookDao

    @Inject
    lateinit var appManager: AppManager

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        onClickListener()
    }

    private fun onClickListener() {
        binding.apply {
            btnLogin.setOnClickListener {
                val username = edtUsername.text.toString()
                val password = edtPassword.text.toString()
                if (username.isEmpty() && password.isEmpty()) {
                    showToast("Form Tidak Boleh Kosong")
                } else {
                    CoroutineScope(Dispatchers.IO).launch {
                        if (accountDao.getAccount(username, password)) {
                            appManager.login(username)
                            saveBook()
                            startActivity(Intent(applicationContext, MainActivity::class.java))
                            finish()
                        }
                    }
                }
            }
        }

        binding.txtRegister.setOnClickListener {
            startActivity(Intent(this, RegisterActivity::class.java))
        }
    }

    private fun saveBook() {
        val listBook = ArrayList<EbookEntity>()
        listBook.add(
            EbookEntity(
                "Tulus untuk Orang yang Salah",
                "https://cdn.gramedia.com/uploads/items/0001_uAnpCBa.jpg",
                "romance",
                "Hubungan satu sisi melibatkan satu orang yang menginvestasikan lebih banyak waktu dan energi ke dalam hubungan daripada pasangan mereka. Padahal, satu orang tidak bisa memikul beban dalam waktu yang lama.\" Aku terluka dan kusembuhkan sendiri. Aku memberi ruang untuk tinggal, tetapi kamu lebih memilih memaksa menepi pergi. Aku bertahan meski kamu sering mencoba merobohkan benteng yang kubangun. Hingga akhirnya aku sadar, aku tulus pada orang yang salah. Aku mencintai sepanjang rasa kalah. Aku tidak pernah benar-benar berharga untukmu.",
                "Boy Chandra"
            )
        )
        listBook.add(
            EbookEntity(
                "Senja, Hujan, & Cerita yang Telah Usai",
                "https://cdnwpseller.gramedia.net/wp-content/uploads/2022/05/11212538/image003-2.png",
                "romance",
                "Dalam buku ini bercerita tentang kisah sedih, patah hati, senja, kenangan yang muncul saat datangnya hujan, dan pada akhirnya akan kembali mengingatkan pada cerita yang telah usai.",
                "Boy Chandra"

            )
        )
        listBook.add(
            EbookEntity(
                "Saat-Saat Jauh",
                "https://cdn.gramedia.com/uploads/items/Saat_Saat_Jauh_cov.jpg",
                "romance",
                "Novel ini mengisahkan tentang Aline, yang mengabdikan dirinya untuk mengurus Panti Jompo J&J di Kota Teduh. Aline rela menunggu Alex, sang kekasih, untuk mengejar impiannya menjadi dokter di Kota Terik. Ketika Alex memutuskan untuk menetap di kota tersebut dan mengajak Aline ikut dengannya, Aline memilih melepaskan Alex dan teguh pada pendiriannya untuk tetap berada di Panti Jompo J&J.",
                "Lia Septia"
            )
        )
        listBook.add(
            EbookEntity(
                "Pulang-Pergi",
                "https://cdn.gramedia.com/uploads/items/pulang-pergi_tere_liye.jpeg",
                "romance",
                "Buku novel Pulang - Pergi ini merupakan karya dari penulis terkenal, yaitu Tere Liye. Buku ini bercerita tentang Bujang yang kembali harus berpetualang setelah pergi dan pulang. Saat Bujang sedang berada di pusara mamak dan bapaknya, Bujang mendapatkan sebuah pesan dari Krestiny Otets, pemimpin brotherhood Bratva.",
                "Tere Liye"
            )
        )
        listBook.add(
            EbookEntity(
                "Serangkai",
                "https://cdn.gramedia.com/uploads/items/551000349_serangkai.png",
                "romance",
                "Tetapi bukan masalah kalau kita nggak selalu bisa berani, Deverra.\n" +
                        "Karena manusia bukan hanya lahir dari tulang, melainkan juga hati.\n" +
                        "Semesta bukan hanya menciptakan tubuh, melainkan juga rasa.\n" +
                        "Dan waktu bukan hanya tentang hari esok, melainkan juga kemarin",
                "Valerie Patkar"
            )
        )


        listBook.add(
            EbookEntity(
                "Seni Merayu Tuhan",
                "https://cdn.gramedia.com/uploads/items/cover_SENI_MERAYU_TUHAN_depan.jpg",
                "philosophy",
                "Allah Maha Indah dan menyukai keindahan, maka dekati dia dengan rayuan yang begitu romantis. Sebab amal kita bukanlah ‘alat tukar’ untuk surga, melainkan hanya Rahmat-Nya yang membawa kita ke surga.",
                "Husein Ja'far Al-Hadar"
            )
        )
        listBook.add(
            EbookEntity(
                "Tuhan Maha Asyik",
                "https://cdn.gramedia.com/uploads/items/9786027926295_Tuhan-Maha-As.jpg",
                "philosophy",
                "Tuhan sangat asyik ketika DIA tidak kita kurung paksa dalam penamaan-penamaan dan pemaknaan-pemaknaan. DIA tidak terdefinisikan, tidak termaknakan. DIA ada sebelum definisi dan makna ada. Tuhan itu anti mainstream. Tuhan itu maha asyik ketika kita mentadabburi-Nya, buka melogikakan-Nya",
                "Sujiwo Tejo"

            )
        )
        listBook.add(
            EbookEntity(
                "Pemimpin Yang Tuhan",
                "https://cdn.gramedia.com/uploads/items/9786022915126_Pemimpin-yang-Tuhan.jpg",
                "philosophy",
                "Setiap orang pasti memilih pemimpin yang bisa dipercaya. Namun percaya membabi buta kepada pemimpin tersebut justru bisa menjadi persoalan. Berprasangka baik memang perbuatan yang dianjurkan. Namun selalu berprasangka baik tanpa sedikit pun meletakkan sikap kritis justru membahayakan. Pemimpin adalah sebuah jabatan di mana orang-orang di sekitarnya menaruh harapan. Bukan jalan agar dirinya menjadi Tuhan dan memaksa orang lain mengikuti apa maunya. Dibanding pemimpin yang lalim, rakyat yang sangat tunduk dan menyerang siapapun yang mengkritik pemimpinnya, justru lebih lalim lagi.",
                "Emha Ainun Nadjib"
            )
        )
        listBook.add(
            EbookEntity(
                "Sedang Tuhan pun Cemburu",
                "https://cdn.gramedia.com/uploads/items/9786022914747_Sedang-Tuhan-pun-Cemburu-Republish.jpg",
                "philosophy",
                "Salah satu bakat paling besar dalam diri manusia memang menjadi binatang: makhluk tingkat ketiga sesudah benda dan tetumbuhan. Binatang plus akal adalah kita. Binatang plus akal plus tataran-tataran lain dari spiritualisme adalah kesempurnaan yang seyogyanya diperjuangkan oleh manusia.",
                "Emha Ainun Nadjib"
            )
        )
        listBook.add(
            EbookEntity(
                "Di Balik Kehendak Tuhan",
                "https://cdn.gramedia.com/uploads/items/9786020489124_Di-Balik-Kehendak-Tuhan.jpg",
                "philosophy",
                "Salah satu bentuk refleksi akal dan hati manusia terhadap beberapa entitas perilaku atau fenomena dalam hidup ini ialah keyakinan bahwa semua itu merupakan ketetapan Tuhan. Namun, tidak banyak dari mereka yang memahami hakikat makna di balik kehendak Tuhan tersebut. Sehingga acapkali sebagian mereka bersikap negatif, putus asa, apatis, atau salah paham menafsirkan beragam kejadian itu.",
                "Rayhan Firdausy"
            )
        )
        CoroutineScope(Dispatchers.IO).launch {
            ebookDao.saveEbook(listBook)
        }
    }

}