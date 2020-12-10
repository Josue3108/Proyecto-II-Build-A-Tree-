using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class Player1Move : MonoBehaviour
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
    public bool Shield=false;

    void Start()
    {
        rb2D=GetComponent<Rigidbody2D>();

        
        
    }
    public void Damage (float x, float y){
        PlayerPrefs.SetFloat("posx",x);
            
        }

    void FixedUpdate()
    {
        if (Input.GetKey("right") ){
            rb2D.velocity = new Vector2 (runSpeed,rb2D.velocity.y);
            spriteRenderer.flipX=false;
            animator.SetBool("Run",true);
        }
        else if (Input.GetKey("left") ){
            rb2D.velocity = new Vector2 (-runSpeed,rb2D.velocity.y);
            spriteRenderer.flipX=true;
            animator.SetBool("Run",true);
        }
        else{
            rb2D.velocity=new Vector2(0,rb2D.velocity.y);
            animator.SetBool("Run",false);
        }
        if (Input.GetKey("up")&& CheckGround.isGrounded){
            rb2D.velocity=new Vector2(rb2D.velocity.x, jumpSpeed);
            animator.SetBool("Run",false);

        }
        if(CheckGround.isGrounded==false){
            animator.SetBool("Jump",true);
            animator.SetBool("Run",false);
        }
        if(CheckGround.isGrounded==true){
            animator.SetBool("Jump", false);
        }
                       
        if(jumpBetter){
            if (rb2D.velocity.y<0){
                rb2D.velocity +=Vector2.up*Physics2D.gravity.y*(fallMultiplier)*Time.deltaTime;
            }
            if (rb2D.velocity.y>0 && !Input.GetKey("up")){
                rb2D.velocity +=Vector2.up*Physics2D.gravity.y*(jumpLowMultiplier)*Time.deltaTime;

            }
        }

        if (Shield==false){
            if(VerAttackLeft.colli && Input.GetKey("g")){
                if (ForcePush){
                    rb2D.velocity = new Vector2 (20,rb2D.velocity.y);
                }else{
                    rb2D.velocity = new Vector2 (15,rb2D.velocity.y);
                } 
                
            }
            if (VerAttackRight.colli && Input.GetKey("g")){
                if (ForcePush){
                    rb2D.velocity = new Vector2 (-20,rb2D.velocity.y);
                }else{
                    rb2D.velocity = new Vector2 (-15,rb2D.velocity.y);
                } 
            }
        }else{
            rb2D.velocity = new Vector2 (0,rb2D.velocity.y);
            Shield=false;
        }
    }
        
}

