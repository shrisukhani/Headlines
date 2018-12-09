# _Headlines_

## Description 

Headlines is a simple Android Application that displays a list of articles that made the headlines where the most recent headlines are on the top and the least recent on the bottom. Each item in the feed is a story. Every story shows the Headline of the article, the name of the publication, an image related to the article if there is one, the time of publishing the article and a short description of the article. 

**Note:** This project was simply so that I could get more comfortable with Android, make my first app that I can show someone and not be totally embarrassed about. I use Litho+Sections because it seemed like a super cool Library and my future employer created and uses it for all lists in their Android app. ~~I'm also using Dagger2 to gain familiarity with how Dependency Injection systems work.~~ (I had planned on using Dagger2 for learning DI but it seemed like overkill for a relatively simple thing and decided against it)

## Technologies Used

* Retrofit and OkHttp Libraries for Networking
* Talks to [NewsAPI](https://newsapi.org/)
* [`Mockito`](https://site.mockito.org/) for Unit Testing
* [`Litho` and `Sections`](https://fblitho.com) for the List
* ~~`Dagger2` for Dependency Injection.~~

## Tasks

- [x] Create the Project
- [x] Initialize `Git` Repo
- [x] Get NewsAPI Key
- [x] Add `Litho+Sections` Dependencies
- [ ] ~~Add `Dagger2` Dependencies~~ [WILL NOT BE DOING]
- [ ] ~~Figure out how `Dagger2` works and what you can use it for. Definitely usable for `NewsAPIService`~~
- [x] Create a new `Application` class and change Manifest accordingly
- [x] Create `HeadlinesListItemSpec` to display a news article item
- [ ] Create Unit Test for `HeadlinesListItemSpec`
- [x] Make the `NewsAPIService` in Retrofit (Add an `OkHttp Interceptor` to add API Key as Header, **DON'T** send the API key as a query parameter
- [x] Make Unit Test for `NewsAPIService` if applicable otherwise add a note here saying Not Applicable and why not
- [x] Make the `HeadlinesListSectionSpec` which will render the `HeadlinesListItem` Components and fetch the data. Figure out exactly how this is supposed to work. Shouldn't be too tough. If you're doing something too complicated it's probably wrong so don't do it
- [ ] Create Unit Test for `HeadlinesListSectionSpec`
- [x] Use the section as the view for `HeadlinesActivity` and voila! You're done! 🙂
