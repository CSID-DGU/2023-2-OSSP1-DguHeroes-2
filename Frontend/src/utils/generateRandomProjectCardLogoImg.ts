import sampleCardImg1 from 'assets/images/card/card_img1.png'
import sampleCardImg2 from 'assets/images/card/card_img2.png'
import sampleCardImg3 from 'assets/images/card/card_img3.png'
import sampleCardImg4 from 'assets/images/card/card_img4.png'
import sampleCardImg5 from 'assets/images/card/card_img5.png'

export const generateRandomProjectCardLogoImg = (key: number) => {
  const randomKey = Math.floor(key) % 5

  if (randomKey === 0) {
    return sampleCardImg1
  }
  if (randomKey === 1) {
    return sampleCardImg2
  }
  if (randomKey === 2) {
    return sampleCardImg3
  }
  if (randomKey === 3) {
    return sampleCardImg4
  }
  if (randomKey === 4) {
    return sampleCardImg5
  }
}
