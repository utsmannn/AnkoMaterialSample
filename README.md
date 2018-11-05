# Anko New Material Design Sample
Kita akan mencoba mengadaptasi Material Design versi baru (https://material.io) pada Anko Layout

## Preview
|Drawer Material|Typography|SnackBar Material|Bottom AppBar|
|--|--|--|--|
|![](https://image.ibb.co/k5W3tL/Screenshot-Anko-Material-Sample-20181106-024651.png)|![](https://image.ibb.co/jmtKHf/Screenshot-Anko-Material-Sample-20181106-024736.png)|![](https://image.ibb.co/fdqzHf/image.png)|![](https://image.ibb.co/ijFAOL/Screenshot-Anko-Material-Sample-20181106-024832.png)|

## APK Preview
[Download apk (4mb)](https://github.com/kucingapes/AnkoMaterialSample/blob/master/app-debug-anko-material-sample.apk?raw=true)

## Index
- [BaseView](#baseview)
- [Tipografi](#typo)
- [Ripple](#ripple)
- [Statusbar dan toolbar](#toolbar)
- [ProgressBar on toolbar](#progress)
- [CardView](#card)
- [Bottom AppBar](#bottom_appbar)
- [Material Snackbar](#snackbar)
- [Material Drawer](#drawer)


## 1. <a name= "baseview" ><a> BaseView
Baseview membuat pada tiap-tiap view konsisten, kita hanya membuat satu master view untuk digunakan pada tiap-tiap view.<br/>
Misalnye kita buat object ```BaseUi``` yang isinya fungsi ```baseText()``` untuk master textview dengan menerapkan typeface.
```kotlin
object BaseUi {
...
    fun ViewManager.baseText() = textView {
        typeface = Typeface.SANS_SERIF
    }
...
}
```
dan untuk menggunakannya
```kotlin
import com.contoh.package.BaseUi.baseText

baseText().apply {
  text = "contoh text dengan typeface sans serif"
}
```

## 2. <a name= "typo" ><a> Typografi
Material design mempunyai ciri tipografi yang jelas dan efisien yang mencangkup typeface, font dan letterspacing.<br>
Typeface yang digunakan biasanya 'sans-serif' dengan font light sampe medium (jarang yg bold) dan letterspace yang ideal.
Dalam hal ini kita tidak perlu lagi menambahkan third-font, karena secara default typeface sudah tersedia sans-serif.<br>

![](https://image.ibb.co/bOfsV0/image.png)
### Penerapan
Cukup seting typeface dan letterspacing pada ```baseText()``` tadi
```kotlin
  typeface = Typeface.SANS_SERIF
  letterSpacing = 0.03f
```
Kita juga dapat membuat fungsi untuk material tipografi tersebut dalam class ```Utils```

```kotlin
fun materialFont(view: TextView) {
    view.apply {
        typeface = Typeface.SANS_SERIF
        letterSpacing = 0.03f
    }
}
```
Nah sekarang fungsi ```materialFont``` sudah bisa di pake untuk tiap textView dan Button
```kotlin
textView("textnya") {
    Utils.materialFont(this)
}
```

[Link code material text](https://github.com/kucingapes/AnkoMaterialSample/blob/master/app/src/main/java/com/kucingapes/ankomaterialsample/MaterialUtils.kt#L42)

## 3. <a name= "ripple" ><a> Ripple
Jika kita menggunakan xml, untuk mengaktifkan animasi ripple cukup dengan menambahkan ```?android:attr/selectableItemBackground``` pada foreground atau background view.<br>
Lalu untuk penggunaan pada Anko, kodenya sebagai berikut

```kotlin
val rippleValue = TypedValue()
context.theme.resolveAttribute(android.R.attr.selectableItemBackground, rippleValue, true)
foreground = ContextCompat.getDrawable(context, rippleValue.resourceId)

// atau di background
// background = ContextCompat.getDrawable(context, rippleValue.resourceId)
```
Kita dapat membuat fungsi tersebut di dalam class ```Utils``` kemudian dipanggil pada view di anko sehingga bisa dipakai berulang
```kotlin
fun ripple(view: View, type: Int) {
    view.apply {
        val rippleValue = TypedValue()
        context.theme.resolveAttribute(android.R.attr.selectableItemBackground, rippleValue, true)

        when (type) {
            0 -> foreground = ContextCompat.getDrawable(context, rippleValue.resourceId)
            1 -> background = ContextCompat.getDrawable(context, rippleValue.resourceId)
        }
    }
}
```
Fungsinya dapat dipanggil seperti ini
```kotlin
cardView {
  Utils.ripple(this, 0) // 0 untuk foreground dan 1 untuk foreground
}
```

| not ripple |ripple|
|--|--|
| ![](https://image.ibb.co/gGA1q0/20181102-214433.gif) | ![](https://image.ibb.co/j13eA0/20181102-214453.gif) |


[Link code ripple](https://github.com/kucingapes/AnkoMaterialSample/blob/master/app/src/main/java/com/kucingapes/ankomaterialsample/MaterialUtils.kt#L52)

## 4. <a name= "toolbar" ><a> StatusBar dan Toolbar

|![](https://image.ibb.co/dsCToL/image.png)|![](https://image.ibb.co/bGsuTL/image.png)|![](https://image.ibb.co/eAQba0/image.png)|
|--|--|--|
|Chrome|Play Games|Keep|

Pada banyak aplikasi google yang menerapkan Material Design 2, status bar dan toolbar memiliki warna yang sama. Caranya adalah dengan menyamakan ```colorPrimary``` dengan ```colorPrimaryDark```. Kemudian pada kita juga akan mengapikasikan tipografi pada title toolbar, caranya dengan custom toolbar yg pake theme ```themedToolbar```.<br>
Kita buat sebuah baseView ```baseToolbar```
```kotlin
fun ViewManager.baseToolbar() = themedToolbar(R.style.ThemeOverlay_AppCompat_Dark) {
    id = R.id.toolbar
    title = resources.getString(R.string.app_name)

    val viewTitle = this.getChildAt(0) as TextView
    materialFont(viewTitle)
}
```
Pada custom toolbar tersebut, kita memanggil textView untuk diaplikasikan fungsi tipografi

![](https://image.ibb.co/iNSi50/image.png)

[Link code toolbar](https://github.com/kucingapes/AnkoMaterialSample/blob/0ae2d43d4d84ddc42e8b5405baceac05d127b3a3/app/src/main/java/com/kucingapes/ankomaterialsample/BaseUi.kt#L46)
<br> [Link code fake status bar](https://github.com/kucingapes/AnkoMaterialSample/blob/0ae2d43d4d84ddc42e8b5405baceac05d127b3a3/app/src/main/java/com/kucingapes/ankomaterialsample/BaseUi.kt#L72)
<br> [Link code disable statusbar](https://github.com/kucingapes/AnkoMaterialSample/blob/0ae2d43d4d84ddc42e8b5405baceac05d127b3a3/app/src/main/java/com/kucingapes/ankomaterialsample/BaseActivity.kt#L20)

## 5. <a name= "progress" ><a> ProgressBar in Toolbar
ProgressDialog sudah deprecated di level api 26 karena kata google itu menutup interaksi user dengan aplikasi. Maka solusi nya adalah menggunakan progressbar yang diletakan secara baik, salah satunya di Toolbar. Caranya tambahkan progressbar ke dalam toolbar.

```kotlin
horizontalProgressBar {
    bottomPadding = dip(-7)
    topPadding = dip(-7)

    isIndeterminate = true
    visibility = View.VISIBLE
    backgroundColorResource = android.R.color.white
}.lparams(matchParent, wrapContent)
```
Visible dapat di setel jika progress on, jika sudah off, bisa disetel ```View.GONE```, ```bottomPadding``` dan ```topPadding``` menghilangkan background putih di atas dan dibawahnya. Background putih disarankan agar membedakan dengan background toolbar dan progress primary.

|![](https://image.ibb.co/f6w2yL/gifeditor-20181103-161700.gif)|
|--|
|progressbar in toolbar|


[link code progressbar](https://github.com/kucingapes/AnkoMaterialSample/blob/0ae2d43d4d84ddc42e8b5405baceac05d127b3a3/app/src/main/java/com/kucingapes/ankomaterialsample/BaseUi.kt#L61)

## 6. <a name= "card" ><a> CardView
Tidak banyak perbedaan cardview pada Material Design 2, cuman pada roundcorner nya yang lebih bulet.
```kotlin
cardView {
    radius = 10f
}
```
|sebelum|sesudah|
|--|--|
|![](https://image.ibb.co/k4Vg8L/image.png)|![](https://image.ibb.co/ddbJNf/image.png)|

## 7. <a name= "bottom_appbar" ><a> Bottom AppBar
Salah satu komponen yang ditambahkan pada material design adalah BottomAppBar, komponen ini berbeda dengan bottom navigation. Kalo di android material (AndroidX) kita gak usah bikin custom view lagi, nah untuk Anko kita harus bikin custom view dengan komposisi
```
cordinator layout
----- vertical layout
---------- view shadow top
---------- toolbar v7
----- fab anchor ke vertical layout di atas
```

Maka kodenya kurang lebih kaya begini
```kotlin
coordinatorLayout{
    // parent layout
    
    ...
    verticalLayout {
        id = bottom_app_bar
        view {
           background = ContextCompat.getDrawable(context, R.drawable.shadow)
        }
        toolbar {
            id = bottom_toolbar
            backgroundColorResource = R.color.colorPrimary
        }
    }.lparams { gravity = Gravity.Bottom }
    
    floatingActionButton {
        setImageResource(R.drawable.ic_plus)
    }.lparams {
        anchorId = bottom_app_bar
        anchorGravity = Gravity.CENTER_HORIZONTAL
        margin = dip(8)
    }
}
```

Untuk view shadow codenya
```xml
<?xml version="1.0" encoding="utf-8"?>
<shape xmlns:android="http://schemas.android.com/apk/res/android">
    <gradient
        android:startColor="#1F000000"
        android:endColor="@android:color/transparent"
        android:angle="90" />
</shape>
```

|![](https://image.ibb.co/chiJ50/image.png)|
|--|
|Custom bottom appbar|

### Menambahkan menu dan navigasi
Pada toolbar yang ada di atas (id bottom_toolbar) bisa kita pasang navigasi icon dan menu, caranya sama kaya toolbar biasa
```kotlin
...
override fun onCreate(...) {
    ...
    val bottomToolbar = find(bottom_toolbar)
    bottomToolbar.navigationIconResource = R.drawable.ic_menu // setup navigasi
    bottomToolbar.inflateMenu(R.menu.bottom_menu) // setup menu
    ...
}
```

|![](https://image.ibb.co/jxWXXf/image.png)|
|--|
|custom bottom appbar with menu and navigation|

Kita juga bisa mengganti floatingActionButton dengan CardView seperti aplikasi [Google Tasks](https://play.google.com/store/apps/details?id=com.google.android.apps.tasks&hl=en). Seting cardview dengan ```radius = 50f```

|![](https://image.ibb.co/hkJMsf/image.png)|
|--|
|custom bottom appbar with cardview|


[Link code bottom appbar](https://github.com/kucingapes/AnkoMaterialSample/blob/master/app/src/main/java/com/kucingapes/ankomaterialsample/BottomAppBarActivity.kt#L110)

## 8. <a name= "snackbar" ><a> Snackbar
Perubahan tampilan untuk snackbar pada Material Design 2 cukup terlihat, snackbar jadi bergaya card.<br>

|![source: material.io](https://image.ibb.co/kDKqL0/image.png)|
|--|
|material snackbar|

Nah kita akan mencoba merubah snackbar biasa menjadi lebih material. Untuk itu kita perlu menambahkan view snackbar ke view custom yang kita buat dan mendisable child view asli seperti textView dan button, Kemudian membuat fungsi untuk textview dan button pada view custom.<br>
Pertama buat class ```SnackbarUi``` untuk layout snackbar

```kotlin
class SnackBarUi(private val marginBottom: Int) : AnkoComponent<ViewGroup> {
    override fun createView(ui: AnkoContext<ViewGroup>): View = with(ui) {
        relativeLayout {
            id = R.id.snackbar_layout
            lparams(matchParent, wrapContent)

            cardView {
                radius = 7f

                linearLayout {
                    gravity = Gravity.CENTER_VERTICAL
                    backgroundColor = Color.parseColor("#323232")
                    textView {
                        id = R.id.snackbar_text
                        textColorResource = android.R.color.white
                        BaseUi.materialFont(this)
                    }.lparams(matchParent, wrapContent) {
                        weight = 3f
                        leftMargin = dip(16)
                        rightMargin = dip(16)
                    }

                    styledButton(R.style.Widget_AppCompat_Button_Borderless) {
                        id = R.id.snackbar_action
                        textColorResource = R.color.colorAccent
                    }.lparams(wrapContent, wrapContent)

                }.lparams(matchParent, wrapContent)
            }.lparams(matchParent, wrapContent) {
                leftMargin = dip(12)
                rightMargin = dip(12)
                topMargin = dip(12)
                bottomMargin = dip(marginBottom)
            }
        }
    }

    inline fun ViewManager.styledButton(styleRes: Int = 0, init: Button.() -> Unit): Button = ankoView({
        if (styleRes == 0) Button(it)
        else Button(ContextThemeWrapper(it, styleRes), null, 0)
    }, 0, init)
}
```

Class ```SnackBarUi``` membawa kontruktor ```marginBottom``` agar margin bottom dari card sifatnya dinamis. Hal ini berguna karena pengaturan dalam Snackbar yang baru **tidak mengizinkan mengoverlap actionview** dibawah layar seperti appbar bottom atau bottom navigasi, jadi perlu ketiggian yang dinamis.
<br>

Setelah itu kita buat class ```MaterialSnackbar```

```kotlin
class MaterialSnackbar private constructor(context: Context) {
    private var background: Int = 0
    var contentView: View? = null
        private set
    private var duration: LENGTH? = null
    private var swipe: Boolean = false
    private var bottomMargin: Int = 12
    private var snackbar: Snackbar? = null

    private var ctx = context

    val isShowing: Boolean
        get() = snackbar != null && snackbar!!.isShown

    init {
        this.duration = LENGTH.LONG
        this.background = -1
        this.swipe = true
    }


    fun duration(duration: LENGTH): MaterialSnackbar {
        this.duration = duration
        return this
    }

    fun swipe(swipe: Boolean): MaterialSnackbar {
        this.swipe = swipe
        return this
    }

    fun bottomMargin(bottomMargin: Int) : MaterialSnackbar {
        this.bottomMargin = bottomMargin
        return this
    }

    fun build(view: View): MaterialSnackbar {
        when (duration) {
            MaterialSnackbar.LENGTH.INDEFINITE -> snackbar = Snackbar.make(view, "", Snackbar.LENGTH_INDEFINITE)
            MaterialSnackbar.LENGTH.SHORT -> snackbar = Snackbar.make(view, "", Snackbar.LENGTH_SHORT)
            MaterialSnackbar.LENGTH.LONG -> snackbar = Snackbar.make(view, "", Snackbar.LENGTH_LONG)
        }
        val snackbarView = snackbar?.view as Snackbar.SnackbarLayout

        if (!swipe) {
            snackbarView.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {
                override fun onPreDraw(): Boolean {
                    snackbarView.viewTreeObserver.removeOnPreDrawListener(this)
                    (snackbarView.layoutParams as CoordinatorLayout.LayoutParams).behavior = null
                    return true
                }
            })
        }

        snackbarView.setPadding(0, 0, 0, 0)
        snackbarView.setBackgroundResource(android.R.color.transparent)
        val text = snackbarView.findViewById<View>(android.support.design.R.id.snackbar_text) as TextView
        text.visibility = View.INVISIBLE
        val action = snackbarView.findViewById<View>(android.support.design.R.id.snackbar_action) as TextView
        action.visibility = View.INVISIBLE
        contentView = SnackBarUi(bottomMargin).createView(AnkoContext.create(ctx, snackbarView))
        snackbarView.addView(contentView, 0)
        return this
    }

    fun setText(text: CharSequence): MaterialSnackbar {
        val textView = contentView?.find<TextView>(R.id.snackbar_text)
        textView?.text = text
        return this
    }

    fun setAction(text: CharSequence, listener: View.OnClickListener): MaterialSnackbar {
        val actionView = contentView?.find<Button>(R.id.snackbar_action)
        actionView?.text = text
        actionView?.visibility = View.VISIBLE
        actionView?.setOnClickListener { view ->
            listener.onClick(view)
            dismiss()
        }
        return this
    }

    fun show() {
        snackbar?.show()
    }

    fun dismiss() {
        if (snackbar != null) snackbar?.dismiss()
    }

    enum class LENGTH {
        INDEFINITE, SHORT, LONG
    }

    companion object {

        fun Builder(context: Context): MaterialSnackbar {
            return MaterialSnackbar(context)
        }
    }
}
```

Pada class diatas setidaknya ada beberapa fungsi, yakni ```duration```, ```swipe```, ```bottomMargin```. Ketiga fungsi tersebut harus dijalankan sebelum fungsi ```build```. Kemudian setelah fungsi ```build```, kita bisa memanggil fungsi ```setText``` dan ```setAction```. Hal ini karena fungsi ```build``` membangun custom layout yang berisi custom text dan button action, jadi setelah ```build``` kedua view tersebut baru bisa dipake.

Untuk menggunakannya kodenya seperti berikut

```kotlin    
MaterialSnackbar.Builder(this)
    .duration(MaterialSnackbar.LENGTH.INDEFINITE) // optional, defaultnya LONG
    .bottomMargin(100) // optional, defaultnya 12dip
    .swipe(false) // optional, defaultnya false
    .build(find(R.id.parent))
    .apply { 
        setText("ini material snackbar")
        setAction("okeh", View.OnClickListener {
            // aksi oke
        })
    }.show()
```

|default|dengan bottomMargin|
|--|--|
|![](https://image.ibb.co/epts00/20181104-011919.gif)|![](https://image.ibb.co/cMnkL0/20181104-012646.gif)|


[Link MaterialSnackbar](https://github.com/kucingapes/AnkoMaterialSample/tree/master/app/src/main/java/com/kucingapes/ankomaterialsample/materialSnackBar)

## <a name= "drawer" ><a> Bonus - Material Drawer

|Material Drawer|
|--|
|![](https://image.ibb.co/mNqzHf/image.png)|

<br>

[Link Class Material Drawer](https://github.com/kucingapes/AnkoMaterialSample/tree/master/app/src/main/java/com/kucingapes/ankomaterialsample/materialDrawer)
<br> [Link Penggunaan Material Drawer](https://github.com/kucingapes/AnkoMaterialSample/blob/master/app/src/main/java/com/kucingapes/ankomaterialsample/MainActivity.kt#L111)

---
**Sip mantap**
