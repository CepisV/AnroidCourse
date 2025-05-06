package kz.step.jsonplaceholder.di

import kz.step.jsonplaceholder.presentation.albums.AlbumsViewModel
import kz.step.jsonplaceholder.presentation.comments.CommentsViewModel
import kz.step.jsonplaceholder.presentation.curr_profile.CurrProfileViewModel
import kz.step.jsonplaceholder.presentation.curr_profile.ToDosViewModel
import kz.step.jsonplaceholder.presentation.posts.PostsViewModel
import kz.step.jsonplaceholder.presentation.posts.details.PostDetailsViewModel
import kz.step.jsonplaceholder.presentation.user_profile.UserProfileViewModel
import kz.step.jsonplaceholder.presentation.users.UsersFragmentViewmodel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


val viewModelModule = module {
    viewModel { PostsViewModel(get()) }
    viewModel { (postId: Int) ->
        PostDetailsViewModel(
            postId = postId,
            getPostByIdUseCase = get(),
            getPostCommentsUseCase = get(),
            getUserUseCase = get()
        )
    }
    viewModel { (postId: Int) ->
        CommentsViewModel(
            postId = postId,
            getPostCommentsUseCase = get()
        )
    }
    viewModel {
        AlbumsViewModel(get())
    }

    viewModel {
        UsersFragmentViewmodel(get())
    }

    viewModel {(userId: Int) ->
        UserProfileViewModel(
            userId = userId,
            getUserUseCase = get()
        )
    }

    viewModel { (userId: Int) ->
        CurrProfileViewModel(
            userId = userId,
            getUserUseCase = get()
        )
    }

    viewModel {(userId : Int) ->
        ToDosViewModel(
            userId = userId,
            getUserToDosUseCase = get())
    }
}
