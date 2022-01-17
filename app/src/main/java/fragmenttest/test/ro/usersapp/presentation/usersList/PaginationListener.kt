package fragmenttest.test.ro.usersapp.presentation.usersList

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class PaginationListener(
    private val layoutManager: LinearLayoutManager
) : RecyclerView.OnScrollListener() {

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        if (dy > 0) {
            val visibleItemCount = layoutManager.childCount
            val totalItemCount = layoutManager.itemCount
            val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

            if (
                visibleItemCount + firstVisibleItemPosition >= (totalItemCount - 3) &&
                firstVisibleItemPosition >= 0 &&
                totalItemCount >= PAGE_SIZE
            ) {
                //loads more data when you reach item 17
                loadMoreItems()
            }
        }
    }

    protected abstract fun loadMoreItems()

    companion object {
        private const val PAGE_SIZE = 20
    }
}