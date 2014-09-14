//
//  TopSpotsTableViewCell.swift
//  SEEKRET
//
//  Created by Constantin Scheuermann on 8/28/14.
//  Copyright (c) 2014 nomis-development.net. All rights reserved.
//

import UIKit

class TopSpotsTableViewCell: UITableViewCell {

   
    @IBOutlet weak var fullSpotNameLabel: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
        fullSpotNameLabel.font = UIFont (name: "HelveticaNeue-Light", size: 15)
    }

    override func setSelected(selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func setCell(spotName: String){
        self.fullSpotNameLabel.text = spotName
    }
}
