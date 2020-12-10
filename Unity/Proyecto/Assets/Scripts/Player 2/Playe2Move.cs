using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Playe2Move : MonoBehaviour
{   

    public float runSpeed=2;

    public float jumpSpeed=5;

    public Rigidbody2D rb2D;

    public bool jumpBetter=false;

    public float fallMultiplier =0.5f;

    public float jumpLowMultiplier =1f;

    public SpriteRenderer spriteRenderer;

    public Animator animator;

    private float posx,posy;

    public bool ForcePush=false; 

    void Start()
    {
        rb2D=GetComponent<Rigidbody2D>();

        
        
    }
    public void Damage (float x, float y){
        PlayerPrefs.SetFloat("posx",x);
            
        }

    void FixedUpdate()
    {
        if (Input.GetKey("d") ){
            rb2D.velocity = new Vector2 (runSpeed,rb2D.velocity.y);
            spriteRenderer.flipX=false;
            animator.SetBool("Run",true);
        }
        else if (Input.GetKey("a") ){
            rb2D.velocity = new Vector2 (-runSpeed,rb2D.velocity.y);
            spriteRenderer.flipX=true;
            animator.SetBool("Run",true);
        }
        else{
            rb2D.velocity=new Vector2(0,rb2D.velocity.y);
            animator.SetBool("Run",false);
        }
        if (Input.GetKey("w")&& CheckGround2.isGrounded){
            rb2D.velocity=new Vector2(rb2D.velocity.x, jumpSpeed);
            animator.SetBool("Run",false);

        }
        if(CheckGround2.isGrounded==false){
            animator.SetBool("Jump",true);
            animator.SetBool("Run",false);
        }
        if(CheckGround2.isGrounded==true){
            animator.SetBool("Jump", false);
        }
                       
        if(jumpBetter){
            if (rb2D.velocity.y<0){
                rb2D.velocity +=Vector2.up*Physics2D.gravity.y*(fallMultiplier)*Time.deltaTime;
            }
            if (rb2D.velocity.y>0 && !Input.GetKey("w")){
                rb2D.velocity +=Vector2.up*Physics2D.gravity.y*(jumpLowMultiplier)*Time.deltaTime;

            }
        }

        

        if(VerAttack2Left.colli && Input.GetKey("l")){
            if (ForcePush){
                rb2D.velocity = new Vector2 (20,rb2D.velocity.y);
            }else{
                rb2D.velocity = new Vector2 (15,rb2D.velocity.y);
            }            
        }
        if(VerAttack2Right.colli && Input.GetKey("l")){            
            if (ForcePush){
                rb2D.velocity = new Vector2 (-20,rb2D.velocity.y);
            }else{
                rb2D.velocity = new Vector2 (-15,rb2D.velocity.y);
            } 
        }
            
            
    }
        
        
}
