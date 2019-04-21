package com.moelle.deepdarkness

import android.content.Context
import android.util.AttributeSet
import androidx.viewpager.widget.ViewPager
import com.github.paolorotolo.appintro.util.LayoutUtil
class AppIntroViewPager(context: Context, attrs: AttributeSet) : ViewPager(context, attrs) {

    private var pageChangeListener: ViewPager.OnPageChangeListener? = null


    override fun setCurrentItem(currentItem: Int) {
    val oldItem = super.getCurrentItem()
    super.setCurrentItem(currentItem)

    // When you pass set current item to 0,
    // The pageChangeListener won't be called so we call it on our own
    if (oldItem == 0 && currentItem == 0) {
        pageChangeListener?.onPageSelected(0)
    }
}
    fun goToNextSlide() {
        currentItem += if (!LayoutUtil.isRtl(context)) 1 else -1
    }

internal open class OnPageChangeListenerAdapter(
        private val onPageScrollStateChanged: ((state: Int) -> Unit)? = null,
        private val onPageScrolled:
                ((position: Int, positionOffset: Float, positionOffsetPixels: Int) -> Unit)? = null,
        private val onPageSelected: ((position: Int) -> Unit)? = null
): ViewPager.OnPageChangeListener {
    override fun onPageScrollStateChanged(state: Int) =
            onPageScrollStateChanged?.invoke(state) ?: Unit

    override fun onPageScrolled(position: Int, positionOffset: Float, positionOffsetPixels: Int) =
            onPageScrolled?.invoke(position, positionOffset, positionOffsetPixels) ?: Unit

    override fun onPageSelected(position: Int) = onPageSelected?.invoke(position) ?: Unit
}}