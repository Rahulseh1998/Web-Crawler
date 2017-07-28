
CREATE TABLE `pages` (
  `id` int(11) NOT NULL,
  `title` varchar(256) NOT NULL,
  `url` text NOT NULL,
  `meta_keyword` text,
  `meta_description` text,
  `noOfLinks` int(11) NOT NULL,
  `noOfImages` int(11) NOT NULL,
  `text` text NOT NULL,
  `pageRank` double NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;


ALTER TABLE `pages`
  ADD PRIMARY KEY (`id`);
  
  ALTER TABLE `pages`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=302;
