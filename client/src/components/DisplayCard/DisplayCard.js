import { useState } from "react";
import {Snackbar} from "@mui/material"
import { styled } from "@mui/material/styles";
import Card from "@mui/material/Card";
import CardHeader from "@mui/material/CardHeader";
import CardMedia from "@mui/material/CardMedia";
import CardContent from "@mui/material/CardContent";
import CardActions from "@mui/material/CardActions";
import Collapse from "@mui/material/Collapse";
import Avatar from "@mui/material/Avatar";
import IconButton from "@mui/material/IconButton";
import Typography from "@mui/material/Typography";
import { red } from "@mui/material/colors";
import FavoriteIcon from "@mui/icons-material/Favorite";
import ShareIcon from "@mui/icons-material/Share";
import ExpandMoreIcon from "@mui/icons-material/ExpandMore";
import MoreVertIcon from "@mui/icons-material/MoreVert";
import Button from "../Button/Button";
import styles from "./displayCard.module.css";
import { likePost,commentPost } from "@/service/homeService";
// import photo from "../../../../server/images/"

const ExpandMore = styled((props) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? "rotate(0deg)" : "rotate(180deg)",
  marginLeft: "auto",
  transition: theme.transitions.create("transform", {
    duration: theme.transitions.duration.shortest,
  }),
}));

export default function DisplayCard({posts,setPosts}) {
  const [openSnackBar, setOpenSnackBar] = useState(false);
  const [snackBarMessage, setSnackBarMessage] = useState();
  const [userComment, setUserComment] = useState();

  const handleExpandClick = (index) => {
    const postUpdate=[...posts];
    postUpdate[index].expanded=!postUpdate[index].expanded;
    setPosts(postUpdate);
  };
  const handleLikePost = async (post,index)=>{
    const likedPostList = [...posts];
    likedPostList[index].isLiked = !likedPostList[index].isLiked;
    try {
      let payload={
        postId:post?.id,
        isLiked:likedPostList[index].isLiked
      }
      const response = await likePost(payload);
      setPosts(likePost);
      console.log("liked post successfully: ",response.data);
    } catch (error) {
      console.log("Error while liking the post: ",error);
      setOpenSnackBar(true);
      setSnackBarMessage("Error while liking the post");
    }
  }
  const handleAddComment = async (post)=>{
    try {
      let payload={
        postId:post?.id,
        comment:userComment
      }
      const response = await commentPost(payload);
      console.log("Comment added successfully");
      setUserComment();
    } catch (error) {
      console.log("Error while commenting on the post: ",error);
      setOpenSnackBar(true);
      setSnackBarMessage("Error while commenting on the post");
    }
  }
  return (
    <div className={styles.main}>
    {posts?.map((post,index)=>(

    <Card sx={{ maxWidth: 345 }} key={index}>
      <CardHeader
        avatar={
          <Avatar sx={{ bgcolor: red[500] }} aria-label="recipe">
            R
          </Avatar>
        }
        action={
          <IconButton aria-label="settings">
            <MoreVertIcon />
          </IconButton>
        }
        title="Shrimp and Chorizo Paella"
        subheader="September 14, 2016"
      />
      <CardMedia
        component="img"
        width={194}
        height="auto"
        src={`/images/${post?.fileName}`}
        alt={post?.caption}
      />
      <CardActions disableSpacing>
        <IconButton aria-label="add to favorites" onClick={()=>handleLikePost(post,index)}>
          <FavoriteIcon />
        </IconButton>
        <IconButton aria-label="share">
          <ShareIcon />
        </IconButton>
      </CardActions>
      <CardContent sx={{paddingTop:0}}>
        <Typography variant="body2" color="text.secondary">
          (User here) {post?.caption}
        </Typography>
      </CardContent>
      <CardActions disableSpacing>
      <Typography variant="body2" color="text.secondary">
          See comments..
        </Typography>
        <ExpandMore
          expand={post?.expanded}
          onClick={()=>handleExpandClick(index)}
          aria-expanded={post?.expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon />
        </ExpandMore>
      </CardActions>
      <Collapse in={post?.expanded} timeout="auto" unmountOnExit>
        <CardContent>
          <Typography paragraph>
            (username) Comment 1
          </Typography>
          <Typography paragraph>
          (username) Comment 2
          </Typography>
          <Typography paragraph>
          (username) Comment 3
          </Typography>
          <div className={styles.comment_container}>
          <input placeholder="Add comment" className={styles.input} onChange={(e)=>setUserComment(e.target.value)}></input>
          <button className={styles.button} onClick={()=>handleAddComment(post)}>Send</button>
          </div>
        </CardContent>
      </Collapse>
    </Card>
    ))}
    <Snackbar
        anchorOrigin={{ vertical:'top', horizontal:'right' }}
        open={openSnackBar}
        onClose={()=>{
          setOpenSnackBar(false)
          setSnackBarMessage()
        }}
        message={snackBarMessage}
        key={'top' + 'right'}
      />
    </div>
  );
}
