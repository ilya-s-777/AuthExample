package controllers;

import models.Post;
import play.data.Form;
import play.mvc.Controller;
import play.mvc.Result;
import views.html.news.add;
import views.html.news.news;

import java.util.List;

public class News extends Controller {

    private static final Form<Post> _postForm = Form.form(Post.class);

    public static Result index(){
        List<Post> posts = Post.find.all();

        return ok(news.render(posts));
    }

    public static Result details(long id){
        Post post = Post.find.byId(id);

        if(post != null){
           Form<Post> filledForm = _postForm.fill(post);

            return ok(add.render(filledForm));
        }

        return badRequest();
    }

    public static Result add(){
        return ok(add.render(_postForm));
    }

    public static Result save(){

        Form<Post> boundForm = _postForm.bindFromRequest();
        if(boundForm.hasErrors()) {
            flash("error", "Please correct the form below.");
            return badRequest(add.render(boundForm));
        }
        Post product = boundForm.get();
        Post post = Post.find.byId(product.id);

        if(post != null) {
            product.update();
        }else{
            product.save();
        }

        flash("success",
                String.format("Successfully added product %s", product));
        return redirect(routes.News.index());
//
    }

    public static Result delete(long id){

        final Post product = Post.find.byId(id);
        if(product == null) {
            return notFound(String.format("Product %s does not exists.", id));
        }
        product.delete();
        return redirect(routes.News.index());

    }


}
