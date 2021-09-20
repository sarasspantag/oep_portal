/*
SQLyog Community v12.18 (32 bit)
MySQL - 5.0.67-community-nt : Database - oep_portal
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`oep_portal` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `oep_portal`;

/*Table structure for table `alumni_blog` */

DROP TABLE IF EXISTS `alumni_blog`;

CREATE TABLE `alumni_blog` (
  `id` int(11) NOT NULL auto_increment,
  `headline` varchar(512) default NULL,
  `content` text,
  `image` varchar(512) default NULL,
  `description` varchar(512) default NULL,
  `created_at` date default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `alumni_blog` */

insert  into `alumni_blog`(`id`,`headline`,`content`,`image`,`description`,`created_at`,`created_by`,`status`) values 
(1,'6 areas demanding CIO attention in the new normal','<p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">The past one year has almost re-defined the priorities for any CIO. As the world took a pause, the problems created by the pandemic brought to light the necessity of being agile, the necessity to have a modular design, the importance of security and most importantly the need for being connected in a virtual world. The inevitability for being available from across the globe has put the CIOs on high alert and has left them with no choice other than transformation.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">While digital transformation has always been necessary, the current pandemic has forced CIOs to quickly transition to a new way of working. This unprecedented and overnight transition has given nightmares to even the biggest of IT organizations. Remote working now has become a way of life, and short term measures taken now, can become permanent fixtures. The pandemic has given a wake-up call for all CIOs, and has forced them to think about new priorities.<br style=\"box-sizing: border-box;\">Based on my experience, I would like to suggest six key areas of focus that will be crucial for CIOs in the new normal:</p><ol style=\"box-sizing: border-box; margin: 0px 0px 20px 15px; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: inherit; font-family: BwModelica-Regular; vertical-align: baseline; list-style-position: initial; list-style-image: initial; color: rgb(35, 35, 35);\"><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Cloud, Cloud and Cloud!</span><br style=\"box-sizing: border-box;\"><em style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-variant: inherit; font-weight: inherit; font-stretch: inherit; font-size: inherit; line-height: inherit; font-family: inherit; vertical-align: baseline;\">â??You need to get to the future first, ahead of your customers, and be ready to greet them before they arrive.â?? â?? Marc Benioff, Founder, CEO, and Chairman of Salesforce</em><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; vertical-align: baseline;\">The above statement beautifully sums up the how quickly an organization needs to move to stay relevant and take a competitive edge in this new normal. In this new world where remote working is the norm, the cloud is playing a pivotal role in the pandemic, thanks to its proven abilities of scale, resilience and reach. The cloud is also a top choice for CIOs as it can help them in undertaking digital transformation initiatives at rapid pace with faster innovation. With ready to use services and infrastructure, the cloud has become a model of choice for driving digital transformation.</p></li><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Data Privacy, Cybersecurity and Compliance</span><br style=\"box-sizing: border-box;\">As the world moves towards a more convenient way of working, the dangers of coming under cyber attacks have increased. Data security, privacy and compliance can be rated as one of the top priority items on the checklist for every CIO, as even a slight glitch in data privacy and security may lead to serious security breaches.</li><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">New Ways of Working</span><br style=\"box-sizing: border-box;\">Today, it seems highly unlikely that we will be able to see the earlier way of working in the future. Work from home policies are expected to remain in effect for a major part of 2021. CIOs will be required to strengthen their remote working policies. From reviewing remote access policies and tools, migration to cloud data centers and SaaS applications to defining protocols for remote meetings, there is a lot that needs to be done and that too with an eye on cybersecurity. CIOs will be required to integrate remote working in their enterprise processes. This will require collaboration with the HR department and the senior management.</li><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Data and Analytics</span><br style=\"box-sizing: border-box;\">Today, data surrounds us. The dependency on data has been like never before, with organizations relying on data, and specifically the insights that arise from data, to navigate a new path forward.Due to high volatility in the ecosystem there is an increased need to make data-led decisions. Thus, data and analytics will be amongst the top most priorities for any CIO. This coupled with cloud Infrastructure usage will define the future path for organizations. Being on cloud will enable solutions across many of the areas (e.g. data warehouses, data science platforms, real-time business-friendly analytics) to be deployed faster as compared to doing a greenfield implementation on-premise.</li><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Modular Architecture</span><br style=\"box-sizing: border-box;\"><em style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-variant: inherit; font-weight: inherit; font-stretch: inherit; font-size: inherit; line-height: inherit; font-family: inherit; vertical-align: baseline;\">â??Composable business means creating an organization made from interchangeable building blocksâ?? â?? Gartner</em><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; vertical-align: baseline;\">The pandemic has exposed many loopholes in the business models that organizations once thought to be efficient. Organizations that had a more modular setup were the ones that bounced back quickly to face the challenges posed by the outbreak. CIOs need to look at this aspect with a long-term vision and transform their organizations to a more composable and modular setup. The modular setup enables a business to reorganize and reshape as required depending on multiple internal and external factors including a shift in customer values, change in market dynamics or sudden change in supply chain or materials. Apart from a modular design, application and infrastructure modernization is one major area that will require a hard and long look by the CIOs. Reducing the technical debt, lighting the application portfolio, leveraging API driven architecture, moving to cloud and moving away from legacy and mainframes will drive the future agenda for growth.</p></li><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Investment in Emerging Technologies</span><br style=\"box-sizing: border-box;\">Invest now and invest big. The pandemic has accelerated the adoption of emerging technologies specially the ones that enable remote working. With social distancing technologies coming into play, more and more automation will be required. The cloud and AI will remain the first choice of technologies. Other equally important technologies include IoT, Big Data, Analytics, Robotics etc. For the future, CIOs need to pay attention on the next set of technologies that will take the businesses forward. Some of these may include technologies like Formative AI, Edge Computing, Data Fabrics, AI Augmented Development, Secure Access Service Edge (SAFE) etc.</li></ol><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">With limited capital in hand to invest, CIOs must take a judicious call to invest in the right technologies, as this will play a crucial role in charting their organizationâ??s future growth.</p>','herofiles\\documents\\product\\blogimage\\1.jpg','','2021-04-22',1,1),
(2,'The Next Normal: What makes an organization a great place to work?','<p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">It is often the small acts of kindness that can have a lasting impact on the way employees perceive organizations. One single act can show employees the value system, the richness of culture, and a sense of belonging. This aspect is specifically critical in the Covid-19 situation, where organizations are grappling with an unprecedented humanitarian crisis. It is a time of fear and uncertainty. At times like these, there has to be decisive leadership and clear communication. It must be fact-based and consistent for leaving out any room for ambiguity.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">Insights from a survey conducted by Bersin &amp; Associates, a leading industry research firm, show that an HR organizationâ??s response due to the Covid-19 pandemic has fallen into four vital areas. These include:</p><ul style=\"box-sizing: border-box; margin: 0px 0px 20px 7px; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: inherit; font-family: BwModelica-Regular; vertical-align: baseline; list-style-position: outside; list-style-image: initial; color: rgb(35, 35, 35);\"><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\">Physical health and wellbeing</li><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\">Remote working</li><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\">Issues related to jobs</li><li style=\"box-sizing: border-box; margin: 0px 0px 0px 1em; padding: 3px 0px; border: none; font: inherit; vertical-align: baseline;\">Work continuity.</li></ul><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">Hence, leaders must have a natural sense of empathy and must exhibit a level of sensitivity and concern that makes it comfortable for employees to reach out. They must provide regular updates to employees as they worry about their jobs and their future.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Culture is something that is not defined.</span></p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">Before the outbreak, few firms had remote work policies. Today, almost all firms have been forced to adopt remote-working policies. This shift has led to many new challenges. The focus on employee productivity and engagement has shifted to immediate response. Employers must ensure that they offer complete support to their employees, be it financially or mentally during the pandemic. Regular communication with employees will help companies to resolve questions proactively.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Summary</span></p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">Culture is something that is not defined or cast in stone. It is what companies exhibit and employees feel, especially during times of crisis. Building a great workplace is a journey, and not a destination. Times like these can be looked at as opportunities to improve and excel. An authentic human connection has a significant impact on how employees perceive your organization now and in the future.</p>','herofiles\\documents\\product\\blogimage\\2.jpg','','2021-04-22',1,1),
(3,'Innovative Mindset â?? Beginning at the Grassroot Level','<p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">Automation is no more a buzz word in the industry, as it was ten years ago. The mindset of the employees took a paradigm shift in their way of thinking as automation mindset evolved into an â??Innovationâ?? mindset, and now â??Innovationâ?? is emerging as â??Business as Usualâ??.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">To trigger the innovation mindset among the employees, organizations must start with workforce transformation, workplace transformation and then start applying them for client-centric innovation.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Workforce Transformation</span></p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">Cultivating the seed of â??Innovative thinkingâ?? within the mindset of the employees, rather than teaching them on automation or process efficiencies should be the starting point. Invite volunteers within the organization or application streams, who are interested in getting certified with industry leading automation tools. In two to three months, approximately 10% of your workforce would show interest in training themselves or getting certified. In addition to that, launch an â??Idea portalâ?? to facilitate the employees within the organization to log their ideas for innovation. A governance model and process should be created to vet these ideas on a regular basis, assess the return on investment in implementing those ideas and prioritizing the logged items for implementation. You would be surprised to receive ideas from multiple streams within your organization.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Workplace Transformation</span></p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">When I went for grocery shopping to Walmart last year on a Black Friday, I was surprised to see all the shopping carts filled with gadgets and items on sale. I did not have any intention to buy that day, other than groceries, however the sight of overflowing shopping carts and the long line-ups at cashiers caused me to panic and feel like I am missing something â??bigâ??, and forced me to buy a smart TV on sale, even though I did not have any idea to purchase one. The analogy here is to the work environment and ambience that should be created in the workplace, to kindle the mindset of employees on Innovative thinking, which would unintentionally yield them to the innovative thought process and make them believe they are missing something if they are not part of this transformation journey.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">One real-time example that clicked in our organization is launching â??Digital receptionistâ?? using Google Home which was placed in our front office to register a visitor, handle parcel delivery, and send notifications to the receiver. This was one of our first interventions into the Artificial Intelligence space by customizing the Google DialogFlow. Every staff member who entered office had to cross this receptionist each day, that would slowly inject the idea of digitalism in their mindset. Interestingly, no one would have anticipated in mid-2019 that a situation like Covid would make â??contactless receptionistâ?? as the new normal, and we already had a solution for this unprecedented situation.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">The next suggestion is to launch an â??Innovation Labâ??. This doesnâ??t require a sophisticated closed room. A cubicle in the vicinity of your employees in the same working floor can be selected for this. Utilize this space to launch demos of applications focused on innovation using cutting-edge technologies. Setup kiosks to spread awareness on these innovative tools and facilitate nurturing of the innovative mindset on the floor.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">Client-centric Innovation</span></p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">Driving innovation would not be serving its full purpose if it is not applied to our clients. Using the transformed workforce, engage in discovery sessions with your clients. Offering a discovery session with the clientâ??s workforce (either free of cost or for a minimal cost) for a week or two, and identifying process inefficiencies, bottlenecks in their day-to-day operations, areas of streamlining can help them realize operational issues faster.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">This should not be limited with only discovery sessions, but also extend the offerings on proof of concepts to help them identify and appreciate the value of automation and innovation in their world.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 22px; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">To conclude, innovation is a journey without any destination, and it requires pit stops to refuel your thought process on what is needed for the hour. What was innovative a year ago would be obsolete now, especially with the disruptions due to Covid and its impact on everyoneâ??s lives. Hence, the best way to propel innovation is to germinate the â??Innovativeâ?? mindset within our employees, especially those at the ground level as they understand the customer better and can cascade the insights to the managers and executives, to get direction on the strategy to structure it, and making it a success for our clients and also for our own organization. Start socializing the concept of innovation within your team and change your mindset to apply innovation in your day-to-day work.</p>','herofiles\\documents\\product\\blogimage\\3.jpg','','2021-04-22',1,1),
(4,'Harnessing the immense power of the cloud with the Snowflakeâ??s platform','<p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 17pt; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\"><span style=\"box-sizing: border-box; margin: 0px; padding: 0px; border: none; font-style: inherit; font-variant: inherit; font-stretch: inherit; font-size: inherit; line-height: 28px; font-family: BwModelica-Bold; vertical-align: baseline;\">A Framework for Maximizing Value from your Snowflake Investments</span><br style=\"box-sizing: border-box;\">Snowflake provides enormous power in the hands of its users. In the past, big data analytical workloads would often be constrained by the ability to scale up compute and storage resources. Customers had to manage a fix amount of capacity. This is no longer the case now, as unlimited resources can be provisioned and made available in seconds with Snowflake. Accidentally deleting data was a nightmare in the past. With Snowflake features such as&nbsp;<a href=\"https://docs.snowflake.com/en/user-guide/data-time-travel.html\" rel=\"noopener\" target=\"_blank\" style=\"box-sizing: border-box; background-color: transparent; color: rgb(0, 0, 140); margin: 0px; padding: 0px; border: none; font: inherit; vertical-align: baseline; outline: 0px; max-width: 100%;\">time travel</a>, users of Snowflake can go back in history (up to 90 days) and can restore data that was corrupted or accidentally deleted. The power and capabilities in the hands of users increased more than a hundredfold after switching to Snowflake. With this power must come responsibility and accountability.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 17pt; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">Moving from a capacity model to a cost-effective consumption model enforces a shift in the way the users interact with the system. Only limits now are customer imagination and their budget. At Larsen and Toubro Infotech, while working with customers during the early days of their snowflake journey, we have observed several scenarios where Snowflake was not being used in the optimal way. One of the customers was truncating and loading data into a database table every day while time travel was enabled for 90 days. What this means in crude terms is on every load the table is deleted and reloaded with all the data. This table alone occupied 90 times storage space as compared to its current size without having that requirement from the business. Another customer was running a query for several hours and the query failed at the end because of poor design. The query was consuming resources without providing any value.</p><p style=\"box-sizing: border-box; margin-top: 0.85em; margin-bottom: 0.85em; padding: 0px; border: none; font-variant-numeric: inherit; font-variant-east-asian: inherit; font-stretch: inherit; font-size: 11pt; line-height: 17pt; font-family: BwModelica-Regular; vertical-align: baseline; color: rgb(35, 35, 35);\">LTI as a Global Service Partner for Snowflake has created the framework and tools to make sure that any dollar spent with Snowflake is aligned to business results. This is available through LTI Canvas Polar Sled FinOps. It integrates controls, best practices, Machine Learning, automation and cost mapping to get the most value for Snowflake investment.</p>','herofiles\\documents\\product\\blogimage\\4.jpg','','2021-04-22',1,1);

/*Table structure for table `alumni_blog_comments` */

DROP TABLE IF EXISTS `alumni_blog_comments`;

CREATE TABLE `alumni_blog_comments` (
  `id` int(11) NOT NULL auto_increment,
  `comments` text,
  `created_at` date default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  `blog_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `alumni_blog_comments` */

/*Table structure for table `alumni_blog_likes` */

DROP TABLE IF EXISTS `alumni_blog_likes`;

CREATE TABLE `alumni_blog_likes` (
  `id` int(11) NOT NULL auto_increment,
  `comment_id` int(11) NOT NULL,
  `likes_count` int(11) NOT NULL,
  `created_at` date default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `alumni_blog_likes` */

/*Table structure for table `alumni_blog_reply` */

DROP TABLE IF EXISTS `alumni_blog_reply`;

CREATE TABLE `alumni_blog_reply` (
  `id` int(11) NOT NULL auto_increment,
  `comment_id` int(11) NOT NULL,
  `reply_content` text,
  `created_at` date default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `alumni_blog_reply` */

insert  into `alumni_blog_reply`(`id`,`comment_id`,`reply_content`,`created_at`,`created_by`,`status`) values 
(1,1,'ok','2020-01-10',1,1),
(2,2,'fdg','2020-01-10',1,1),
(3,3,'bfcghr','2020-01-20',1,1);

/*Table structure for table `contact_us` */

DROP TABLE IF EXISTS `contact_us`;

CREATE TABLE `contact_us` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(512) default NULL,
  `email` varchar(512) default NULL,
  `mobile` varchar(512) default NULL,
  `content` text,
  `created_date` datetime default NULL,
  `created_by` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `contact_us` */

/*Table structure for table `course_master` */

DROP TABLE IF EXISTS `course_master`;

CREATE TABLE `course_master` (
  `course_id` int(11) NOT NULL auto_increment,
  `course_name` varchar(255) NOT NULL,
  `course_desc` text NOT NULL,
  `course_details` varchar(1024) NOT NULL,
  `duration` int(11) NOT NULL,
  `applicable_ic` int(11) NOT NULL,
  `created_date` date NOT NULL,
  `modified_date` date default NULL,
  `created_by` int(11) NOT NULL,
  `modified_by` int(11) default NULL,
  `status` int(11) default '1',
  PRIMARY KEY  (`course_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `course_master` */

insert  into `course_master`(`course_id`,`course_name`,`course_desc`,`course_details`,`duration`,`applicable_ic`,`created_date`,`modified_date`,`created_by`,`modified_by`,`status`) values 
(1,'Java Training Course','To develop young trainees in Java','herofiles\\documents\\product\\image\\1.docx',30,1,'2021-04-23','2021-04-23',1,1,1),
(2,'Android ','Android  ','herofiles\\documents\\product\\image\\2.docx',60,1,'2021-04-23','2021-04-23',1,1,0);

/*Table structure for table `course_scheduling` */

DROP TABLE IF EXISTS `course_scheduling`;

CREATE TABLE `course_scheduling` (
  `cs_id` int(11) NOT NULL auto_increment,
  `program_name` varchar(255) default NULL,
  `schedule_name` varchar(128) default NULL,
  `start_date` datetime default NULL,
  `end_date` datetime default NULL,
  `start_time` varchar(512) default NULL,
  `end_time` varchar(512) default NULL,
  `total_participants_allowed` int(128) default '0',
  `applicable_ic` varchar(255) default NULL,
  `faculty_name` varchar(255) default NULL,
  `status` int(11) default '1',
  `created_at` date default NULL,
  PRIMARY KEY  (`cs_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `course_scheduling` */

insert  into `course_scheduling`(`cs_id`,`program_name`,`schedule_name`,`start_date`,`end_date`,`start_time`,`end_time`,`total_participants_allowed`,`applicable_ic`,`faculty_name`,`status`,`created_at`) values 
(1,'1','SCH-00001','2021-04-21 00:00:00','2021-06-08 00:00:00','10:30 am','4:30 pm',30,'1','1',0,'2006-01-01');

/*Table structure for table `debug_table` */

DROP TABLE IF EXISTS `debug_table`;

CREATE TABLE `debug_table` (
  `ID` int(11) NOT NULL auto_increment,
  `VALUE1` varchar(128) default NULL,
  `VALUE2` varchar(128) default NULL,
  `VALUE3` varchar(128) default NULL,
  `VALUE4` text,
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=112 DEFAULT CHARSET=latin1;

/*Data for the table `debug_table` */

insert  into `debug_table`(`ID`,`VALUE1`,`VALUE2`,`VALUE3`,`VALUE4`) values 
(1,NULL,NULL,'else ',NULL),
(2,NULL,NULL,'else if',NULL),
(3,NULL,'1','2',NULL),
(4,NULL,'1','2',NULL),
(5,NULL,NULL,'V_NO_REC_FOUND if',NULL),
(6,NULL,NULL,'V_COURSE_STATUS 0',NULL),
(7,NULL,NULL,NULL,'P_ROLE_ID'),
(8,NULL,NULL,'else ',NULL),
(9,NULL,NULL,'else if',NULL),
(10,NULL,'1','2',NULL),
(11,NULL,'1','2',NULL),
(12,NULL,NULL,'V_NO_REC_FOUND if',NULL),
(13,NULL,NULL,'V_COURSE_STATUS 0',NULL),
(14,NULL,NULL,NULL,'P_ROLE_ID'),
(15,NULL,NULL,'else ',NULL),
(16,NULL,NULL,'else if',NULL),
(17,NULL,'1','2',NULL),
(18,NULL,'1','2',NULL),
(19,NULL,NULL,'V_NO_REC_FOUND if',NULL),
(20,NULL,NULL,'V_COURSE_STATUS 0',NULL),
(21,NULL,NULL,NULL,'P_ROLE_ID'),
(22,'fgfd',NULL,NULL,NULL),
(23,'fgfd',NULL,NULL,NULL),
(24,'11:15 am',NULL,NULL,NULL),
(25,NULL,NULL,'12:45 pm',NULL),
(26,'11:15 am',NULL,NULL,NULL),
(27,NULL,NULL,'12:45 pm',NULL),
(28,'5:15 pm',NULL,NULL,NULL),
(29,NULL,NULL,'7:0 pm',NULL),
(30,NULL,NULL,NULL,'UPD'),
(31,NULL,NULL,NULL,'priya'),
(32,NULL,NULL,NULL,'UPD'),
(33,NULL,NULL,NULL,'priya'),
(34,NULL,NULL,NULL,'2'),
(35,NULL,NULL,NULL,'UPD'),
(36,NULL,NULL,NULL,'priya'),
(37,NULL,NULL,NULL,'2'),
(38,NULL,NULL,NULL,'INS'),
(39,NULL,NULL,NULL,'INS'),
(40,NULL,NULL,NULL,'first if'),
(41,NULL,NULL,'second if',NULL),
(42,NULL,NULL,NULL,'third if'),
(43,NULL,NULL,NULL,'INS'),
(44,NULL,NULL,NULL,'INS'),
(45,NULL,NULL,'else ',NULL),
(46,NULL,NULL,NULL,'invalid  '),
(47,NULL,NULL,NULL,'INS'),
(48,NULL,NULL,NULL,'INS'),
(49,NULL,NULL,NULL,'first if'),
(50,NULL,NULL,'second if',NULL),
(51,NULL,NULL,NULL,'third if'),
(52,'11:15 am',NULL,NULL,NULL),
(53,NULL,NULL,'12:45 pm',NULL),
(54,'5:15 pm',NULL,NULL,NULL),
(55,NULL,NULL,'7:0 pm',NULL),
(56,NULL,NULL,NULL,'UPD'),
(57,NULL,NULL,NULL,'priya'),
(58,NULL,NULL,NULL,'4'),
(59,NULL,NULL,NULL,'UPD'),
(60,NULL,NULL,NULL,'UPD'),
(61,NULL,NULL,NULL,'UPD'),
(62,NULL,NULL,NULL,'UPD'),
(63,NULL,NULL,NULL,'UPD'),
(64,NULL,NULL,NULL,'UPD'),
(65,NULL,NULL,NULL,'UPD'),
(66,NULL,NULL,NULL,'UPD'),
(67,NULL,NULL,NULL,'UPD'),
(68,'5:15 pm',NULL,NULL,NULL),
(69,NULL,NULL,'7:0 pm',NULL),
(70,NULL,NULL,NULL,'UPD'),
(71,'11:15 am',NULL,NULL,NULL),
(72,NULL,NULL,'12:45 pm',NULL),
(73,'5:15 pm',NULL,NULL,NULL),
(74,NULL,NULL,'6:45 pm',NULL),
(75,'4:15 pm',NULL,NULL,NULL),
(76,NULL,NULL,'5:45 pm',NULL),
(77,'4:15 pm',NULL,NULL,NULL),
(78,NULL,NULL,'6:0 pm',NULL),
(79,'4:15 pm',NULL,NULL,NULL),
(80,NULL,NULL,'5:45 pm',NULL),
(81,'fgfd',NULL,NULL,NULL),
(82,'4:15 pm',NULL,NULL,NULL),
(83,NULL,NULL,'6:0 pm',NULL),
(84,'17','0',NULL,NULL),
(85,'17','0',NULL,NULL),
(86,'17','0',NULL,NULL),
(87,'17','0',NULL,NULL),
(88,'17','0',NULL,NULL),
(89,'fgfd',NULL,NULL,NULL),
(90,'17','0',NULL,NULL),
(91,'17','0',NULL,NULL),
(92,'fgfd',NULL,NULL,NULL),
(93,'fgfd',NULL,NULL,NULL),
(94,'fgfd',NULL,NULL,NULL),
(95,NULL,NULL,NULL,'UPD'),
(96,'s',NULL,NULL,NULL),
(97,NULL,NULL,NULL,'INS'),
(98,NULL,NULL,NULL,'INS'),
(99,NULL,NULL,NULL,'first if'),
(100,NULL,NULL,'second if',NULL),
(101,NULL,NULL,NULL,'third if'),
(102,'fgfd',NULL,NULL,NULL),
(103,'6:20 pm',NULL,NULL,NULL),
(104,NULL,NULL,'7:35 pm',NULL),
(105,'6:20 pm',NULL,NULL,NULL),
(106,NULL,NULL,'7:35 pm',NULL),
(107,'9:40 am',NULL,NULL,NULL),
(108,NULL,NULL,'8:55 am',NULL),
(109,'fgfd',NULL,NULL,NULL),
(110,'11:45 am',NULL,NULL,NULL),
(111,NULL,NULL,'2:0 am',NULL);

/*Table structure for table `department` */

DROP TABLE IF EXISTS `department`;

CREATE TABLE `department` (
  `id` int(11) NOT NULL auto_increment,
  `desc` varchar(512) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `department` */

insert  into `department`(`id`,`desc`) values 
(1,'Department-1'),
(2,'Department-2'),
(3,'Department-3');

/*Table structure for table `email_history` */

DROP TABLE IF EXISTS `email_history`;

CREATE TABLE `email_history` (
  `imh_id` int(11) NOT NULL auto_increment,
  `imh_email_id` varchar(256) default NULL,
  `imh_email_subject` varchar(256) default NULL,
  `imh_email_content` text,
  `imh_email_created_at` datetime default NULL,
  `imh_staus` int(11) default NULL,
  `imh_retry_count` int(11) default NULL,
  `imh_response` varchar(128) default NULL,
  `ts_id` int(48) default NULL,
  `part_id` int(48) default NULL,
  PRIMARY KEY  (`imh_id`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;

/*Data for the table `email_history` */

insert  into `email_history`(`imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content`,`imh_email_created_at`,`imh_staus`,`imh_retry_count`,`imh_response`,`ts_id`,`part_id`) values 
(1,'prabha31192@gmail.com','newuser','Dear Users, your new password is \'GUJOWI%6\', try the application with this password.','2006-01-01 07:14:57',0,0,NULL,NULL,NULL),
(2,'prabha31192@gmail.com','Schedule Course','You have been assigned to `Java Training Course` course.Please login to Portal and check details.`http://localhost/#/login`','2006-01-01 07:28:54',1,1,'',NULL,NULL),
(3,'prabhaindrajith@gmail.com','Registration','You have been sucessfully Registered  in L&T.Your Password is DP@499!V','2006-01-01 07:32:23',1,1,'',NULL,NULL),
(4,'prabhaindrajith@gmail.com','Test Request','You have received a Test Request . Please click on the link  to start the test.http://139.59.62.244/#/login?tsid=1','2006-01-01 08:04:08',1,1,'',NULL,NULL),
(5,'prabhaindrajith@gmail.com','Test Request','You have received a Test Request . Please click on the link  to start the test.http://localhost:4200/#/login?tsid=1','2021-04-22 09:32:28',1,1,'',NULL,NULL),
(6,'prabhaindrajith@gmail.com','sendmail','Please click on the Link `http://localhost:6060/onlineexamine/forms/reports/printcoursecertificate/16126` to download your course certificate.','2021-04-22 09:39:15',1,NULL,'',NULL,NULL),
(7,'prabha31192@gmail.com','sendmail','Please click on the Link `http://localhost:6060/onlineexamine/forms/reports/printcoursecertificate/16126` to download your course certificate.','2021-04-22 09:39:15',1,NULL,'',NULL,NULL),
(8,'admin@gmail.com','course certificate','Please click on the Link  http://localhost:6060/onlineexamine/forms/reports/printcoursecertificate/16126  to download your course certificate.','2021-04-22 09:39:30',1,NULL,'',NULL,NULL),
(9,'guest@gmail.com','course certificate','Please click on the Link  http://localhost:6060/onlineexamine/forms/reports/printcoursecertificate/16126  to download your course certificate.','2021-04-22 09:39:38',1,NULL,'',NULL,NULL),
(10,'prabha31192@gmail.com','Test Request','You have received a Test Request . Please click on the link  to start the test.http://localhost:4200/#/login?tsid=1','2021-04-23 11:33:44',1,1,'',NULL,NULL),
(11,'prabhaindrajith@gmail.com','sendmail','Please click on the Link `http://localhost:6060/onlineexamine/forms/reports/printcoursecertificate/10296` to download your course certificate.','2021-04-23 11:42:58',1,NULL,'',NULL,NULL),
(12,'prabhaindrajith@gmail.com','sendmail','Please click on the Link `http://localhost:6060/onlineexamine/forms/reports/printcoursecertificate/10296` to download your course certificate.','2021-04-23 11:42:58',1,NULL,'',NULL,NULL),
(13,'admin@gmail.com','course certificate','Please click on the Link  http://localhost:6060/onlineexamine/forms/reports/printcoursecertificate/10296  to download your course certificate.','2021-04-23 11:43:12',1,NULL,'',NULL,NULL),
(14,'guest@gmail.com','course certificate','Please click on the Link  http://localhost:6060/onlineexamine/forms/reports/printcoursecertificate/10296  to download your course certificate.','2021-04-23 11:43:20',1,NULL,'',NULL,NULL);

/*Table structure for table `email_settings` */

DROP TABLE IF EXISTS `email_settings`;

CREATE TABLE `email_settings` (
  `email_id` varchar(256) default NULL,
  `email_password` varchar(128) default NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `email_settings` */

insert  into `email_settings`(`email_id`,`email_password`) values 
('schedulertestjbintech@gmail.com','@admin123'),
('schedulertestjbintech@gmail.com','@admin123');

/*Table structure for table `email_template` */

DROP TABLE IF EXISTS `email_template`;

CREATE TABLE `email_template` (
  `email_temp_id` int(11) NOT NULL auto_increment,
  `email_temp_name` varchar(128) NOT NULL,
  `email_temp_subject` varchar(256) default NULL,
  `email_temp_content` text,
  `email_temp_appln_type` int(11) default '0',
  `param_desc` varchar(512) default NULL,
  PRIMARY KEY  (`email_temp_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=latin1;

/*Data for the table `email_template` */

insert  into `email_template`(`email_temp_id`,`email_temp_name`,`email_temp_subject`,`email_temp_content`,`email_temp_appln_type`,`param_desc`) values 
(1,'Forgot Password','Forgot Password','Dear Users, your new password is \'$param1\', try the application with this password.',0,NULL),
(2,'Schedule Course','Schedule Course','You have been assigned to `$param1` course.Please login to Portal and check details.`$param2`',0,NULL),
(3,'Registration','Message to Registered Participants','You have been successfully Registered under `param1`   course.',0,NULL),
(4,'Customer Query','New Message','<html>   <head>   <title>HTML email</title>   </head>      <body>          	   	  <table> 	  <tr> <th>Name :</th> <td>\'$param1\'</td>  </tr>  	  <tr> <th>Email :</th> <td>\'$param2\'</td>  </tr>  	  <tr> <th>Contact No :</th> <td>\'$param3\'</td>  </tr> 	  <tr> <th>Content :</th> <td>\'$param4\'</td>  </tr>  	  </table> 	</body>  	   	</html>',0,NULL),
(5,'Course Test new','Test admin','Please click on the below Link `param1` to start the Test.You need to login before taking the test.',0,NULL),
(6,'course certificate','course certificate','Please click on the Link `param1` to download your course certificate.',0,NULL),
(7,'Course Certificate','Course Certificate','',0,NULL),
(8,'fghgjgh','fghjgjhgk','fgjghjhkjh',0,NULL),
(9,'fcxbvcbvn','fvbvnvbm','vcnb mnm',0,NULL),
(10,'sarassdfsd','sarassdfaf','sdfdafasdfsfsdf',0,NULL);

/*Table structure for table `exam_master` */

DROP TABLE IF EXISTS `exam_master`;

CREATE TABLE `exam_master` (
  `id` int(11) NOT NULL auto_increment,
  `exam_id` varchar(255) NOT NULL,
  `sub_id` int(11) NOT NULL,
  `batch` varchar(255) NOT NULL,
  `question_master_id` int(11) NOT NULL,
  `participant_id` int(11) NOT NULL,
  `test_id` int(11) NOT NULL,
  `test_name` varchar(255) NOT NULL,
  `attend` int(11) default '1',
  `mark` int(11) default '0',
  `exam_date` date NOT NULL,
  `duration_from` varchar(255) NOT NULL,
  `duration_to` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `exam_master` */

/*Table structure for table `faculty_master` */

DROP TABLE IF EXISTS `faculty_master`;

CREATE TABLE `faculty_master` (
  `faculty_id` int(11) NOT NULL auto_increment,
  `faculty_firstname` varchar(255) default NULL,
  `faculty_middlename` varchar(512) default NULL,
  `faculty_lastname` varchar(512) default NULL,
  `userid` int(11) NOT NULL,
  `username` varchar(512) NOT NULL,
  `password` varchar(512) NOT NULL,
  `email` varchar(512) NOT NULL,
  `description` text,
  `applicable_ic` varchar(512) default NULL,
  `contact_no` varchar(128) default NULL,
  `gender` varchar(128) default NULL,
  `dob` date default NULL,
  `qualification` varchar(128) default NULL,
  `occupation` varchar(128) default NULL,
  `experience` varchar(512) default NULL,
  `main_subject` varchar(128) default NULL,
  `created_date` date default NULL,
  `modified_date` date default NULL,
  `status` int(11) default '1',
  `ref_no` varchar(255) default NULL,
  `faculty_profile` varchar(512) default 'herofiles\\documents\\faculty\\facultyimage\\10111.jpg',
  PRIMARY KEY  (`faculty_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `faculty_master` */

insert  into `faculty_master`(`faculty_id`,`faculty_firstname`,`faculty_middlename`,`faculty_lastname`,`userid`,`username`,`password`,`email`,`description`,`applicable_ic`,`contact_no`,`gender`,`dob`,`qualification`,`occupation`,`experience`,`main_subject`,`created_date`,`modified_date`,`status`,`ref_no`,`faculty_profile`) values 
(1,'Prabha','Indrajith','Indrajith',12,'prabha','GUJOWI%6','prabhaindrajith@gmail.com',NULL,'1','8903367159','Male','1992-01-31',NULL,'Professor',NULL,'1','2006-01-01','2006-01-01',1,'B3491','herofiles\\documents\\faculty\\facultyimage\\12.jpg');

/*Table structure for table `faculty_master_education_details` */

DROP TABLE IF EXISTS `faculty_master_education_details`;

CREATE TABLE `faculty_master_education_details` (
  `fed_id` int(11) NOT NULL auto_increment,
  `faculty_id` int(11) NOT NULL,
  `qualification` varchar(512) default NULL,
  `course` varchar(512) default NULL,
  `specification` varchar(512) default NULL,
  `university` varchar(512) default NULL,
  `start_date` date default NULL,
  `end_date` date default NULL,
  `created_date` date default NULL,
  `modified_date` date default NULL,
  PRIMARY KEY  (`fed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `faculty_master_education_details` */

insert  into `faculty_master_education_details`(`fed_id`,`faculty_id`,`qualification`,`course`,`specification`,`university`,`start_date`,`end_date`,`created_date`,`modified_date`) values 
(1,12,'BE','MECH','MECH','ANNA UNIVERSITY','2009-09-07','2013-05-21','2006-01-01','2006-01-01');

/*Table structure for table `faculty_master_experience_details` */

DROP TABLE IF EXISTS `faculty_master_experience_details`;

CREATE TABLE `faculty_master_experience_details` (
  `fed_id` int(11) NOT NULL auto_increment,
  `faculty_id` int(11) NOT NULL,
  `designation` varchar(512) default NULL,
  `company_name` varchar(512) default NULL,
  `job_desc` varchar(512) default NULL,
  `start_date` date default NULL,
  `end_date` date default NULL,
  `created_date` date default NULL,
  `modified_date` date default NULL,
  PRIMARY KEY  (`fed_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `faculty_master_experience_details` */

insert  into `faculty_master_experience_details`(`fed_id`,`faculty_id`,`designation`,`company_name`,`job_desc`,`start_date`,`end_date`,`created_date`,`modified_date`) values 
(1,12,'Developer','SpanTag Tech','Developer','2018-11-20','2021-07-14','2006-01-01','2006-01-01');

/*Table structure for table `faculty_master_skills_details` */

DROP TABLE IF EXISTS `faculty_master_skills_details`;

CREATE TABLE `faculty_master_skills_details` (
  `fsd_id` int(11) NOT NULL auto_increment,
  `skill` varchar(512) NOT NULL,
  `efficiency` varchar(512) default NULL,
  `created_date` date default NULL,
  `modified_date` date default NULL,
  `faculty_id` int(11) NOT NULL,
  PRIMARY KEY  (`fsd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `faculty_master_skills_details` */

insert  into `faculty_master_skills_details`(`fsd_id`,`skill`,`efficiency`,`created_date`,`modified_date`,`faculty_id`) values 
(1,'REACT NATIVE ANGULAR JAVA','90','2006-01-01','2006-01-01',12);

/*Table structure for table `gallery_image_category` */

DROP TABLE IF EXISTS `gallery_image_category`;

CREATE TABLE `gallery_image_category` (
  `category_id` int(11) NOT NULL auto_increment,
  `category_name` varchar(255) NOT NULL,
  PRIMARY KEY  (`category_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

/*Data for the table `gallery_image_category` */

insert  into `gallery_image_category`(`category_id`,`category_name`) values 
(1,'All'),
(2,'Buildings'),
(3,'Bridges'),
(4,'Roads');

/*Table structure for table `gallery_image_category_details` */

DROP TABLE IF EXISTS `gallery_image_category_details`;

CREATE TABLE `gallery_image_category_details` (
  `category_image_id` int(11) NOT NULL auto_increment,
  `category_id` int(11) NOT NULL,
  `category_image_path` varchar(255) NOT NULL,
  `category_image_desc` text,
  PRIMARY KEY  (`category_image_id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=latin1;

/*Data for the table `gallery_image_category_details` */

insert  into `gallery_image_category_details`(`category_image_id`,`category_id`,`category_image_path`,`category_image_desc`) values 
(1,2,'herofiles\\documents\\gallery\\galleryimage\\17909.jpg','Lightning Building'),
(2,3,'herofiles\\documents\\gallery\\galleryimage\\11752.jpg','Bridge'),
(3,3,'herofiles\\documents\\gallery\\galleryimage\\19645.jpg','Colorful'),
(4,2,'herofiles\\documents\\gallery\\galleryimage\\26338.jpg','Apartment'),
(5,4,'herofiles\\documents\\gallery\\galleryimage\\27606.jpg','curvy'),
(6,4,'herofiles\\documents\\gallery\\galleryimage\\25831.jpg','conjested'),
(7,4,'herofiles\\documents\\gallery\\galleryimage\\27581.jpg','circular'),
(8,3,'herofiles\\documents\\gallery\\galleryimage\\27886.jpg','moon'),
(9,3,'herofiles\\documents\\gallery\\galleryimage\\28906.jpg','open top'),
(10,3,'herofiles\\documents\\gallery\\galleryimage\\11924.jpg','Red'),
(11,4,'herofiles\\documents\\gallery\\galleryimage\\19419.jpg','tokyo'),
(12,4,'herofiles\\documents\\gallery\\galleryimage\\20618.jpg','clumpsy'),
(13,2,'herofiles\\documents\\gallery\\galleryimage\\24060.jpg',''),
(14,2,'herofiles\\documents\\gallery\\galleryimage\\11956.jpg','stylish'),
(15,3,'herofiles\\documents\\gallery\\galleryimage\\27429.jpg','forest'),
(16,3,'herofiles\\documents\\gallery\\galleryimage\\19713.jpg','Hand Bridge'),
(17,3,'herofiles\\documents\\gallery\\galleryimage\\11968.jpg','mountain Bridge'),
(18,4,'herofiles\\documents\\gallery\\galleryimage\\15181.jpg','curly');

/*Table structure for table `module` */

DROP TABLE IF EXISTS `module`;

CREATE TABLE `module` (
  `moduleid` int(11) NOT NULL auto_increment,
  `modulename` varchar(255) NOT NULL,
  `modulepath` varchar(255) NOT NULL,
  `is_submodule` int(11) NOT NULL,
  `parentid` int(11) default NULL,
  `is_routing` int(11) default NULL,
  `is_superadmin` int(11) default NULL,
  `status` int(11) default NULL,
  `fafafont` varchar(255) default NULL,
  `app_type` int(11) NOT NULL,
  `is_admin_menu` int(11) default NULL,
  `is_report_menu` int(11) default NULL,
  PRIMARY KEY  (`moduleid`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=latin1;

/*Data for the table `module` */

insert  into `module`(`moduleid`,`modulename`,`modulepath`,`is_submodule`,`parentid`,`is_routing`,`is_superadmin`,`status`,`fafafont`,`app_type`,`is_admin_menu`,`is_report_menu`) values 
(1,'Dashboard','dashboard',0,0,2,2,1,'assets/menu-icons/dashboard.png',1,1,0),
(2,'Home','dashboard',0,0,2,2,1,'assets/menu-icons/home.png',1,1,0),
(3,'About-Us','oeep/aboutus',0,0,2,2,1,'assets/menu-icons/aboutus.png',1,1,0),
(4,'Alumini Blog','oeep/aluminiblog',0,0,2,2,1,'assets/menu-icons/alumni.png',1,1,0),
(5,'Contact-Us','oeep/contact-us',0,0,2,2,1,'assets/menu-icons/contact.png',1,1,0),
(6,'Faculty Profile','facultyprofile',0,0,2,2,1,'assets/menu-icons/facultyprofile.png',1,1,0),
(7,'Image Gallery','oeep/Image',0,0,2,2,1,'assets/menu-icons/imagegallery.png',1,1,0),
(8,'News & Events','',0,0,2,2,1,'assets/menu-icons/news.png',1,1,0),
(9,'Training Course Management','coursemastermanagement',0,0,2,2,1,'assets/menu-icons/training.png',1,1,0),
(10,'Course Master Management','coursemastermanagement',1,9,2,2,1,NULL,1,1,0),
(11,'Subject','subject',1,9,2,2,1,'list_alt',1,1,0),
(12,'Course Schedule','course_schedule',1,9,2,2,1,NULL,1,1,0),
(13,'Course Evaluation Module','questionbank_management',0,0,2,2,1,'assets/menu-icons/course.png',1,1,0),
(14,'Question Bank Management','questionbank_management',1,13,2,2,1,NULL,1,1,0),
(15,'Test Schedule','test_schedule',1,13,2,2,1,NULL,1,1,0),
(16,'Test Administrator','testadministrator',1,13,2,2,1,NULL,1,1,0),
(17,'Test Submission','testsubmission',1,13,2,2,1,NULL,1,1,0),
(18,'Test Result','result',1,13,2,2,1,NULL,1,1,0),
(19,'User Management Module','user_management_module',0,0,2,2,1,'assets/menu-icons/user.png',1,1,0),
(20,'Issue Course Certificate','certificate',0,0,2,2,1,'assets/menu-icons/issuecourse.png',1,1,0),
(21,'Course Registration','course_registration',1,9,2,2,1,NULL,1,1,0),
(22,'Participant Profile','participant_profile',0,0,2,2,NULL,NULL,1,1,0),
(23,'Mail Template','template',0,0,2,2,1,'assets/menu-icons/mail.png',1,1,0),
(24,'Gallery','add-gallery',0,0,2,2,1,'assets/menu-icons/imagegallery.png',1,1,0),
(25,'Alumni Blog','add-alumini-blog',0,0,2,2,1,'assets/menu-icons/alumni.png',1,1,0),
(26,'News and Events','add_news',0,0,2,2,1,'assets/menu-icons/news.png',1,1,0),
(27,'Profile','participant_profile',0,0,2,2,1,'assets/menu-icons/user.png',1,1,0);

/*Table structure for table `news_blog` */

DROP TABLE IF EXISTS `news_blog`;

CREATE TABLE `news_blog` (
  `id` int(11) NOT NULL auto_increment,
  `headline` varchar(512) default NULL,
  `content` text,
  `image` varchar(512) default NULL,
  `description` varchar(512) default NULL,
  `created_at` date default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

/*Data for the table `news_blog` */

insert  into `news_blog`(`id`,`headline`,`content`,`image`,`description`,`created_at`,`created_by`,`status`) values 
(1,'Open Platform & Technical Initiatives','<p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\"><span style=\"font-size: 1.1875rem; text-align: inherit; box-sizing: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit; font-weight: 700; line-height: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\">Event Insights and Advanced Reporting&nbsp;</span></span></span></span><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\">-&nbsp;</span></span></span></span></span></span><span style=\"font-size: 1.1875rem; text-align: inherit; box-sizing: inherit;\">Conference is proud to announce the release of a full-service reporting solution that helps you dig deep into your event data. Using advanced audience segmentation techniques, weâ??ll deliver rich insights about your most important events. Our team will partner with you to understand, plan, and report on the data your stakeholders care about most.</span><br></p><ul style=\"box-sizing: inherit; margin: 0px 0px 1rem 1.5625rem; padding: 0px; list-style-position: outside; line-height: 1.6; font-family: &quot;Open Sans&quot;, sans-serif; font-size: 16px; background-color: rgb(254, 254, 254);\"><li style=\"box-sizing: inherit; margin: 0px 0px 0.9375rem; padding: 0px; font-size: 1.1875rem; list-style: none; position: relative; line-height: 1.8;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit; font-weight: 700; line-height: inherit;\"><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\">When to use:&nbsp;&nbsp;</span></span></span><span style=\"box-sizing: inherit;\"><span style=\"box-sizing: inherit;\">Identifying event ROI is one of the primary tasks for any Event Marketer and persists as one of the most difficult challenges, year-over-year. Leverage our advanced business intelligence solution, along with a robust service offering, to maximize the value of the data you collect on your event attendees.</span></span></span></span></span></span></li></ul>','herofiles\\documents\\news\\newsimage\\1.jpg','','2021-04-22',1,1),
(2,'Get to Know The Cvent Client Success Team','<p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">For the past year, many of us have been working from home. While we miss being in the office and seeing each other, the Event Client Success team at&nbsp;Cvent&nbsp;has taken this time to try to get to know each other a little better. We thought it was only appropriate to share what weâ??ve learned about each other with all of you.</p><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Our Event Client Success team is spread out in sixteen different cities across the world. In North America, we have offices&nbsp;in Portland, Oregon; Dallas &amp; Austin, Texas; McLean, Virginia; Philadelphia, Pennsylvania; and Fredericton, New Brunswick. We also have some remote team members in Richmond, Virginia; Weehawken, New Jersey; Charlotte &amp; Raleigh, North Carolina. Across the pond, our team is represented in London, Frankfurt, Dubai, Melbourne, Gurgaon, and Singapore.&nbsp;&nbsp;</p><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">As we just passed the one-year&nbsp;mark since&nbsp;Cvent&nbsp;has been working from home due to the pandemic, we shared our favorite quarantine activities with each other. Some of the most popular activities across the team include cooking, knitting, reading, working out, and binging TV shows and movies, specifically&nbsp;<em style=\"box-sizing: inherit; line-height: inherit;\">Hamilton</em>&nbsp;and the<em style=\"box-sizing: inherit; line-height: inherit;\">&nbsp;Real Housewives</em>. Some other team members have spent their time at home tackling renovations and gardening.</p><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Now, letâ??s get personal and talk fun facts. If there is one thing you take away from this post&nbsp;this&nbsp;is&nbsp;it..the&nbsp;Event Client Success team has some interesting fun facts. Let\'s start with our competitive team members. One was in a dance crew prior to joining us at&nbsp;Cvent, another competitively sailed boats when growing up and someone else has played in blackjack and slot tournaments. Some other fun facts included casual run-ins with Hulk Hogan and Justin Bieber!&nbsp;&nbsp;</p>','herofiles\\documents\\news\\newsimage\\2.jpg','','2021-04-22',1,1),
(3,'10 Ways to Incorporate Social Media into Your Events','<p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Planners and marketers alike want attendees actively posting to social media and using the mobile event app.&nbsp;<a href=\"http://www.cventconnect.com/\" target=\"_blank\" style=\"box-sizing: inherit; background-color: transparent; text-decoration-line: underline; cursor: pointer; line-height: inherit; color: rgb(0, 113, 242); font-weight: 700;\">At our upcoming user conference, Cvent CONNECT</a>, we\'re encouraging our attendees to share experiences, engage with each other, and help this event live beyond the walls of the amazing Mandalay Bay Resort and Casino.</p><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Mobile and social are two besties that can do wonders for your event marketing efforts and your onsite attendee engagement! However, finding new and creative ways to incorporate social media into your events can be challenging. At Cvent, we\'re constantly researching new, fun ways to make social media an integral part of the event experience. So whether your event audience is full of social media novices or professional second-screen multi-taskers, we hope youâ??ll find these ideas helpful when planning how to incorporate social media into your next event!</p><h3 style=\"box-sizing: inherit; margin-top: 3.125rem; margin-bottom: 1rem; padding: 0px; font-family: BrandonText, sans-serif; font-weight: 700; text-rendering: optimizelegibility; line-height: 1.2; font-size: 1.625rem; background-color: rgb(254, 254, 254);\">Networking Photo Challenge</h3><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Set tent cards around your networking event with different photo challenges. Not only does it serve as an ice breaker, it encourages attendees to get social! Make sure your hashtag is included on the challenges. Some examples to consider â?? â??Take a photo with someone new youâ??ve just met!â?? â??Take a photo with someone wearing the same color outfits as you.â?? â??Take a photo with someone who shares your birthday month.â??</p><h3 style=\"box-sizing: inherit; margin-top: 3.125rem; margin-bottom: 1rem; padding: 0px; font-family: BrandonText, sans-serif; font-weight: 700; text-rendering: optimizelegibility; line-height: 1.2; font-size: 1.625rem; background-color: rgb(254, 254, 254);\">Larger than life hashtag</h3><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Who doesnâ??t love big signage? Give your attendees the perfect backdrop to their Instagram photo as well as a big reminder of what your event hashtag is. In fact, put the hashtag on all of your signage, branding and even on attendee name badges!</p><h3 style=\"box-sizing: inherit; margin-top: 3.125rem; margin-bottom: 1rem; padding: 0px; font-family: BrandonText, sans-serif; font-weight: 700; text-rendering: optimizelegibility; line-height: 1.2; font-size: 1.625rem; background-color: rgb(254, 254, 254);\">Mirror Selfie Stations</h3><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Everyone will have to use the bathroom at some point. Bathrooms also tend to have exceptionally good lighting, perfect for taking selfies! Create removable stickers to brand the bathroom mirrors â?? especially full body ones â?? with your event hashtag, quote bubbles, or emojis.</p><h3 style=\"box-sizing: inherit; margin-top: 3.125rem; margin-bottom: 1rem; padding: 0px; font-family: BrandonText, sans-serif; font-weight: 700; text-rendering: optimizelegibility; line-height: 1.2; font-size: 1.625rem; background-color: rgb(254, 254, 254);\">Mobile + Social = Besties</h3><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Prominently feature the event hashtag on your splash screen and banner ads. Use push notifications to remind attendees to share. Ensure all social icons are linked to your brandâ??s accounts as well as encourage speakers, attendees and exhibitors to link their social accounts to their event app profiles.</p><h3 style=\"box-sizing: inherit; margin-top: 3.125rem; margin-bottom: 1rem; padding: 0px; font-family: BrandonText, sans-serif; font-weight: 700; text-rendering: optimizelegibility; line-height: 1.2; font-size: 1.625rem; background-color: rgb(254, 254, 254);\">Social Swag</h3><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Your event hashtag is almost as important as your brandâ??s logo when it comes to swag. Ensure it is on all swag items and consider giving out swag that encourages social behavior. Some ideas to consider â?? device chargers, â??Tweet Meâ?? / â??Snap Meâ?? stickers for name badges, selfie sticks, photo booth props, pens with stylus or hashtag temporary tattoos.</p><h3 style=\"box-sizing: inherit; margin-top: 3.125rem; margin-bottom: 1rem; padding: 0px; font-family: BrandonText, sans-serif; font-weight: 700; text-rendering: optimizelegibility; line-height: 1.2; font-size: 1.625rem; background-color: rgb(254, 254, 254);\">SocialWall</h3><p style=\"box-sizing: inherit; margin-bottom: 2rem; padding: 0px; font-size: 1.1875rem; line-height: 1.8; text-rendering: optimizelegibility; font-family: &quot;Open Sans&quot;, sans-serif; background-color: rgb(254, 254, 254);\">Integrate the social content being generated by your attendees as part of your event design with SocialWal<a href=\"http://www.cvent.com/en/event-management-software/social-wall-interactive-media-display.shtml\" style=\"box-sizing: inherit; background-color: transparent; text-decoration-line: underline; cursor: pointer; line-height: inherit; color: rgb(0, 113, 242); font-weight: 700;\">l</a>. This event technology displays the content on your event hashtag practically anywhere using a projection screen or TVs. Attendees sharing will get excited to see themselves on â??the big screenâ?? and attendees not participating will want to join in on the fun!</p>','herofiles\\documents\\news\\newsimage\\3.jpg','','2021-04-22',1,1);

/*Table structure for table `news_events_blog_comments` */

DROP TABLE IF EXISTS `news_events_blog_comments`;

CREATE TABLE `news_events_blog_comments` (
  `id` int(11) NOT NULL auto_increment,
  `comments` text,
  `created_at` date default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  `news_blog_id` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `news_events_blog_comments` */

/*Table structure for table `news_events_blog_likes` */

DROP TABLE IF EXISTS `news_events_blog_likes`;

CREATE TABLE `news_events_blog_likes` (
  `id` int(11) NOT NULL auto_increment,
  `comment_id` int(11) NOT NULL,
  `likes_count` int(11) NOT NULL,
  `created_at` date default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `news_events_blog_likes` */

/*Table structure for table `news_events_blog_reply` */

DROP TABLE IF EXISTS `news_events_blog_reply`;

CREATE TABLE `news_events_blog_reply` (
  `id` int(11) NOT NULL auto_increment,
  `comment_id` int(11) NOT NULL,
  `reply_content` text,
  `created_at` date default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `news_events_blog_reply` */

/*Table structure for table `oep_no_format` */

DROP TABLE IF EXISTS `oep_no_format`;

CREATE TABLE `oep_no_format` (
  `SEQ_ID` int(11) NOT NULL,
  `TXN_ID` varchar(1) NOT NULL,
  `TXN_START_NAME` char(4) NOT NULL,
  `TXN_DESC` varchar(64) NOT NULL,
  `TXN_NO_OF_ZEROS` varchar(1) NOT NULL,
  `CREATED_BY` varchar(48) NOT NULL,
  `CREATED_AT` datetime NOT NULL,
  `MODIFIED_BY` varchar(48) default NULL,
  `MODIFIED_AT` datetime default NULL,
  PRIMARY KEY  (`TXN_START_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `oep_no_format` */

insert  into `oep_no_format`(`SEQ_ID`,`TXN_ID`,`TXN_START_NAME`,`TXN_DESC`,`TXN_NO_OF_ZEROS`,`CREATED_BY`,`CREATED_AT`,`MODIFIED_BY`,`MODIFIED_AT`) values 
(7,'7','BAT','Batch','5','ADMIN','2019-09-09 09:52:44','1','2019-09-09 09:52:46'),
(4,'4','EXAM','Exam','5','ADMIN','2019-08-21 10:18:26','1','2019-08-21 10:18:17'),
(2,'2','FACT','Faculty','5','ADMIN','2019-08-21 10:18:21','1','2019-08-21 10:19:03'),
(1,'1','PART','Participant','5','ADMIN','2019-08-21 10:18:08','1','2019-08-21 10:18:14'),
(3,'3','QBM','Test','5','ADMIN','2019-08-21 10:18:23','1','2019-08-21 10:18:16'),
(5,'5','REF','Reference','5','ADMIN','2019-08-21 10:18:27','1','2019-08-21 10:18:19'),
(6,'6','REG','Registration','5','ADMIN','2019-08-21 10:17:45','1','2019-08-21 10:17:51'),
(8,'8','SCH','schedule','5','ADMIN','2019-09-30 00:07:06','1','2019-09-30 00:07:19'),
(9,'9','TSH','TEST_SCHEDULE','5','ADMIN','2019-10-01 12:25:53','1','2019-10-01 12:25:56');

/*Table structure for table `oep_question_import_status` */

DROP TABLE IF EXISTS `oep_question_import_status`;

CREATE TABLE `oep_question_import_status` (
  `seq_id` int(11) NOT NULL auto_increment,
  `seq_no` varchar(512) default NULL,
  `question_id` int(11) default NULL,
  `status` varchar(512) default NULL,
  `error_msg` varchar(512) default NULL,
  `uploaded_by` int(11) default NULL,
  `record_seq_no` int(11) default NULL,
  `serialno` int(11) default NULL,
  PRIMARY KEY  (`seq_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `oep_question_import_status` */

insert  into `oep_question_import_status`(`seq_id`,`seq_no`,`question_id`,`status`,`error_msg`,`uploaded_by`,`record_seq_no`,`serialno`) values 
(1,'13303',1,'Success','Details saved Successfully',1,0,1),
(2,'13303',2,'Success','Question Created Successfully',1,1,1),
(3,'13303',3,'Success','Question Created Successfully',1,2,2),
(4,'13303',4,'Success','Question Created Successfully',1,3,3),
(5,'13303',5,'Success','Question Created Successfully',1,4,4),
(6,'13303',6,'Success','Question Created Successfully',1,5,5);

/*Table structure for table `part_certificate_dtls` */

DROP TABLE IF EXISTS `part_certificate_dtls`;

CREATE TABLE `part_certificate_dtls` (
  `id` int(11) NOT NULL auto_increment,
  `part_id` int(11) default NULL,
  `test_id` varchar(512) default NULL,
  `batch` varchar(512) default NULL,
  `ts_id` int(11) default NULL,
  `ques_master_id` int(11) default NULL,
  `random_no` varchar(512) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

/*Data for the table `part_certificate_dtls` */

insert  into `part_certificate_dtls`(`id`,`part_id`,`test_id`,`batch`,`ts_id`,`ques_master_id`,`random_no`) values 
(1,1,'TSH-00001','1',1,1,'25742'),
(2,1,NULL,'1',1,NULL,'10296');

/*Table structure for table `part_final_mark_response` */

DROP TABLE IF EXISTS `part_final_mark_response`;

CREATE TABLE `part_final_mark_response` (
  `id` int(11) unsigned NOT NULL auto_increment,
  `flag` varchar(1) default NULL,
  `response_msg` varchar(255) default NULL,
  `part_id` int(128) default NULL,
  `schedule_id` int(128) default NULL,
  `part_mark` varchar(128) default NULL,
  `full_mark` varchar(128) default NULL,
  `is_pass` int(128) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `part_final_mark_response` */

insert  into `part_final_mark_response`(`id`,`flag`,`response_msg`,`part_id`,`schedule_id`,`part_mark`,`full_mark`,`is_pass`) values 
(1,'S','You have Successfully Submit the Exam. Your Score is',13,1,'30','55',1);

/*Table structure for table `part_ques_details` */

DROP TABLE IF EXISTS `part_ques_details`;

CREATE TABLE `part_ques_details` (
  `id` int(11) NOT NULL auto_increment,
  `part_test_id` int(11) NOT NULL,
  `ind_ques_id` int(11) NOT NULL,
  `ind_ques_ans` varchar(516) default NULL,
  `ind_ques_mark` int(255) default NULL,
  `ind_ques_attempted` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `part_ques_details` */

insert  into `part_ques_details`(`id`,`part_test_id`,`ind_ques_id`,`ind_ques_ans`,`ind_ques_mark`,`ind_ques_attempted`) values 
(1,1,4,'JVM',0,1),
(2,1,2,'Pointers',10,1),
(3,1,5,'Int',10,1),
(4,1,3,'Hexa',10,1),
(5,1,1,'16',0,1),
(6,1,6,'FGBDFT43',0,1);

/*Table structure for table `partcipant_master_education_details` */

DROP TABLE IF EXISTS `partcipant_master_education_details`;

CREATE TABLE `partcipant_master_education_details` (
  `ped_id` int(11) NOT NULL auto_increment,
  `participant_id` int(11) NOT NULL,
  `qualification` varchar(512) default NULL,
  `course` varchar(512) default NULL,
  `specification` varchar(512) default NULL,
  `university` varchar(512) default NULL,
  `start_date` date default NULL,
  `end_date` date default NULL,
  `created_date` date default NULL,
  `modified_date` date default NULL,
  PRIMARY KEY  (`ped_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `partcipant_master_education_details` */

/*Table structure for table `partcipant_master_skills_details` */

DROP TABLE IF EXISTS `partcipant_master_skills_details`;

CREATE TABLE `partcipant_master_skills_details` (
  `psd_id` int(11) NOT NULL auto_increment,
  `participant_id` int(11) NOT NULL,
  `skill` varchar(512) NOT NULL,
  `created_date` date default NULL,
  `modified_date` date default NULL,
  PRIMARY KEY  (`psd_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `partcipant_master_skills_details` */

/*Table structure for table `participants` */

DROP TABLE IF EXISTS `participants`;

CREATE TABLE `participants` (
  `participant_id` int(11) NOT NULL auto_increment,
  `ref_no` varchar(512) default NULL,
  `reg_no` varchar(512) default NULL,
  `reg_type` varchar(512) default NULL,
  `first_name` varchar(512) default NULL,
  `middle_name` varchar(512) default NULL,
  `last_name` varchar(512) default NULL,
  `username` varchar(512) NOT NULL,
  `password` varchar(512) default NULL,
  `department` varchar(512) default NULL,
  `employee_id` varchar(512) default NULL,
  `email_id` varchar(512) NOT NULL,
  `description` text,
  `applicable_ic` varchar(512) default NULL,
  `contact_no` varchar(512) default NULL,
  `jobcode` varchar(512) default NULL,
  `gender` varchar(512) default NULL,
  `dob` date default NULL,
  `year_id` int(11) default NULL,
  `imagepath` varchar(512) default NULL,
  `address` text,
  `city` text,
  `pincode` varchar(512) default NULL,
  `created_date` date NOT NULL,
  `modified_date` date default NULL,
  `user_id` int(11) default NULL,
  PRIMARY KEY  (`participant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `participants` */

insert  into `participants`(`participant_id`,`ref_no`,`reg_no`,`reg_type`,`first_name`,`middle_name`,`last_name`,`username`,`password`,`department`,`employee_id`,`email_id`,`description`,`applicable_ic`,`contact_no`,`jobcode`,`gender`,`dob`,`year_id`,`imagepath`,`address`,`city`,`pincode`,`created_date`,`modified_date`,`user_id`) values 
(1,NULL,NULL,NULL,NULL,NULL,NULL,'Ajay',NULL,NULL,NULL,'prabhaindrajith@gmail.com',NULL,NULL,NULL,'1',NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2006-01-01','2006-01-01',13);

/*Table structure for table `participants_registration_course_details` */

DROP TABLE IF EXISTS `participants_registration_course_details`;

CREATE TABLE `participants_registration_course_details` (
  `prcd_id` int(11) NOT NULL auto_increment,
  `participant_id` int(11) NOT NULL,
  `course_id` int(11) NOT NULL,
  `created_date` date NOT NULL,
  `modified_date` date default NULL,
  `is_approved` int(11) default '0',
  PRIMARY KEY  (`prcd_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `participants_registration_course_details` */

insert  into `participants_registration_course_details`(`prcd_id`,`participant_id`,`course_id`,`created_date`,`modified_date`,`is_approved`) values 
(1,1,1,'2021-04-21','2021-04-21',1);

/*Table structure for table `question_details` */

DROP TABLE IF EXISTS `question_details`;

CREATE TABLE `question_details` (
  `question_id` int(11) NOT NULL auto_increment,
  `ques_master_id` varchar(255) NOT NULL,
  `question` text NOT NULL,
  `question_type` varchar(128) NOT NULL default 'radio',
  `option_1` text NOT NULL,
  `option_2` text NOT NULL,
  `option_3` text NOT NULL,
  `option_4` text NOT NULL,
  `answer` text NOT NULL,
  `image` varchar(512) default NULL,
  `mark` int(255) default NULL,
  `created_date` date NOT NULL,
  `created_by` int(11) NOT NULL,
  `status` int(11) NOT NULL default '1',
  PRIMARY KEY  (`question_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `question_details` */

insert  into `question_details`(`question_id`,`ques_master_id`,`question`,`question_type`,`option_1`,`option_2`,`option_3`,`option_4`,`answer`,`image`,`mark`,`created_date`,`created_by`,`status`) values 
(1,'1','72*(12+18/12*3)','Single-Select','12','18','16','15','12','herofiles\\documents\\product\\image\\1.jpg',5,'2021-04-23',1,1),
(2,'1','Which of the following is not a Java features?','Single-Select',' Dynamic','Architecture Neutral','Pointers','Class','Pointers','-',10,'2021-04-23',1,1),
(3,'1','The \\u0021 article referred to as a','Single-Select','unicde','octal','Hexa','Decimal','Hexa','-',10,'2021-04-23',1,1),
(4,'1','which  is used to find and fix bugs in the Java programs','Single-Select','JVM','JRE','JDK','JBH','JDK','-',10,'2021-04-23',1,1),
(5,'1',' What is the return type of the hashCode() method in the Object class?','Single-Select','OBJECT','Int','long','void','Int','-',10,'2021-04-23',1,1),
(6,'1','Which of the following is a valid long literal?','Single-Select','FGBDFT43','RGF436YV','DF55Y4GF','FGH55HT5','FGH55HT5','-',10,'2021-04-23',1,1);

/*Table structure for table `question_master` */

DROP TABLE IF EXISTS `question_master`;

CREATE TABLE `question_master` (
  `id` int(11) NOT NULL auto_increment,
  `test_id` varchar(255) NOT NULL,
  `test_name` varchar(512) NOT NULL,
  `sub_id` int(11) NOT NULL,
  `batch` varchar(255) NOT NULL,
  `created_by` int(11) default NULL,
  `created_date` date default NULL,
  `status` int(11) NOT NULL default '1',
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `question_master` */

insert  into `question_master`(`id`,`test_id`,`test_name`,`sub_id`,`batch`,`created_by`,`created_date`,`status`) values 
(1,'QBM-00001','Java Test',1,'0',1,'2021-04-23',1);

/*Table structure for table `roles` */

DROP TABLE IF EXISTS `roles`;

CREATE TABLE `roles` (
  `id` int(11) NOT NULL auto_increment,
  `role_name` varchar(255) NOT NULL,
  `description` varchar(1024) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `roles` */

insert  into `roles`(`id`,`role_name`,`description`) values 
(1,'Superadmin','Superadmin for the portal with overall access.'),
(2,'Faculty','Faculties of SIS'),
(3,'IC_Admin','controls the faculties and participants.'),
(4,'participants','Participants of the test'),
(5,'GeneralAudience','The general website visitors.'),
(6,'GuestUser',NULL);

/*Table structure for table `test_administrator` */

DROP TABLE IF EXISTS `test_administrator`;

CREATE TABLE `test_administrator` (
  `id` int(11) NOT NULL auto_increment,
  `test_id` varchar(255) default NULL,
  `test_name` varchar(255) default NULL,
  `batch` varchar(255) default NULL,
  `testdate` date default NULL,
  `part_id` int(11) default NULL,
  `starttime` varchar(255) default NULL,
  `endtime` varchar(255) default NULL,
  `created_by` int(11) default NULL,
  `status` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `test_administrator` */

/*Table structure for table `test_participants` */

DROP TABLE IF EXISTS `test_participants`;

CREATE TABLE `test_participants` (
  `id` int(11) NOT NULL auto_increment,
  `ts_id` int(11) default NULL,
  `part_id` int(11) default NULL,
  `userid` int(11) default NULL,
  `total_mark` bigint(255) default NULL,
  `full_mark` bigint(255) default NULL,
  `is_pass` int(11) default NULL,
  `started_at` datetime default NULL,
  `end_time` datetime default NULL,
  `is_participant_start` int(11) default '0',
  `question_count` int(11) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `test_participants` */

insert  into `test_participants`(`id`,`ts_id`,`part_id`,`userid`,`total_mark`,`full_mark`,`is_pass`,`started_at`,`end_time`,`is_participant_start`,`question_count`) values 
(1,1,1,13,30,55,1,'2021-04-23 11:34:38','2021-04-23 11:36:16',2,6);

/*Table structure for table `test_question_history` */

DROP TABLE IF EXISTS `test_question_history`;

CREATE TABLE `test_question_history` (
  `history_id` int(11) NOT NULL auto_increment,
  `question_id` int(11) NOT NULL,
  `ts_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `question_answer` text,
  `created_date` date default NULL,
  `start_time` varchar(128) default NULL,
  `end_time` varchar(128) default NULL,
  `question_status` varchar(11) NOT NULL,
  PRIMARY KEY  (`history_id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

/*Data for the table `test_question_history` */

insert  into `test_question_history`(`history_id`,`question_id`,`ts_id`,`user_id`,`question_answer`,`created_date`,`start_time`,`end_time`,`question_status`) values 
(1,4,1,13,'JVM','2021-04-23','11:34','11:34','1'),
(2,2,1,13,'Pointers','2021-04-23','11:34','11:34','1'),
(3,5,1,13,'Int','2021-04-23','11:34','11:34','1'),
(4,3,1,13,'Hexa','2021-04-23','11:34','11:35','1'),
(5,1,1,13,'16','2021-04-23','11:35','11:35','1'),
(6,6,1,13,NULL,'2021-04-23','11:35','','-1');

/*Table structure for table `test_schedule` */

DROP TABLE IF EXISTS `test_schedule`;

CREATE TABLE `test_schedule` (
  `id` int(11) NOT NULL auto_increment,
  `ques_master_id` int(11) default NULL,
  `is_test_started` int(11) default '0',
  `test_schedule_id` varchar(512) default NULL,
  `batch` varchar(512) default NULL,
  `testdate` date default NULL,
  `start_time` varchar(512) default NULL,
  `end_time` varchar(512) default NULL,
  `total_questions` int(255) default NULL,
  `total_participant_attempted` int(255) default NULL,
  `created_by` int(255) default NULL,
  `created_at` datetime default NULL,
  `administrated_by` int(11) default '0',
  `administrated_at` datetime default NULL,
  `status` int(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

/*Data for the table `test_schedule` */

insert  into `test_schedule`(`id`,`ques_master_id`,`is_test_started`,`test_schedule_id`,`batch`,`testdate`,`start_time`,`end_time`,`total_questions`,`total_participant_attempted`,`created_by`,`created_at`,`administrated_by`,`administrated_at`,`status`) values 
(1,1,1,'TSH-00001','1','2021-04-23','11:45 am','2:0 am',6,1,1,'2021-04-23 11:31:46',1,'2021-04-23 11:33:44',1);

/*Table structure for table `todo_list` */

DROP TABLE IF EXISTS `todo_list`;

CREATE TABLE `todo_list` (
  `id` int(11) NOT NULL auto_increment,
  `content` text,
  `created_by` int(11) default NULL,
  `created_at` date default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `todo_list` */

/*Table structure for table `token` */

DROP TABLE IF EXISTS `token`;

CREATE TABLE `token` (
  `token_id` int(10) NOT NULL auto_increment,
  `token_key` varchar(128) default NULL,
  `token_loginout_status` int(11) default NULL,
  `token_ud_userid` int(11) default NULL,
  `token_ud_username` varchar(258) default NULL,
  `token_ud_role` int(11) default NULL,
  `token_in_time` datetime default NULL,
  `token_out_time` datetime default NULL,
  PRIMARY KEY  (`token_id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Data for the table `token` */

insert  into `token`(`token_id`,`token_key`,`token_loginout_status`,`token_ud_userid`,`token_ud_username`,`token_ud_role`,`token_in_time`,`token_out_time`) values 
(1,'4424480887965723487',0,2,'',1,'2021-04-22 09:23:00',NULL),
(2,'1898609810431666925',0,1,'',1,'2021-04-22 09:23:20',NULL),
(3,'1831892700447671763',0,2,'',1,'2021-04-22 09:24:42',NULL),
(4,'3439076024040963008',0,2,'',1,'2021-04-22 09:31:47',NULL),
(5,'1464382234532057989',0,1,'',1,'2021-04-22 09:31:58',NULL),
(6,'4538185656249586028',0,2,'',1,'2021-04-22 09:33:10',NULL),
(7,'3255580615993003993',0,13,'',4,'2021-04-22 09:33:23',NULL),
(8,'7795773401238643753',0,2,'',1,'2021-04-22 09:35:28',NULL),
(9,'917450708000990777',0,1,'',1,'2021-04-22 09:35:47',NULL),
(10,'2808719264365726068',0,2,'',1,'2021-04-22 10:17:32',NULL),
(11,'548449860181561042',0,2,'',1,'2021-04-22 10:19:12',NULL),
(12,'6965309430769993578',0,2,'',1,'2021-04-22 10:19:46',NULL),
(13,'4503353275984285842',0,2,'',1,'2021-04-22 10:21:46',NULL),
(14,'3952796200304779763',0,2,'',1,'2021-04-22 10:23:28',NULL),
(15,'8154637839083198024',0,2,'',1,'2021-04-22 10:24:12',NULL),
(16,'5870815050857401630',0,2,'',1,'2021-04-22 10:26:42',NULL),
(17,'9134823010293923186',0,2,'',1,'2021-04-22 10:28:35',NULL),
(18,'181964714076928353',0,2,'',1,'2021-04-22 10:31:03',NULL),
(19,'3078408838489564861',0,2,'',1,'2021-04-22 10:38:58',NULL),
(20,'873854600246333995',0,2,'',1,'2021-04-22 10:40:29',NULL),
(21,'8708489625802930027',0,2,'',1,'2021-04-22 10:41:53',NULL),
(22,'8701104108020327379',0,2,'',1,'2021-04-22 10:59:09',NULL),
(23,'2602828487716514532',0,2,'',1,'2021-04-23 11:19:39',NULL),
(24,'2724236555779229704',0,1,'',1,'2021-04-23 11:22:00',NULL),
(25,'9116728240245388435',0,2,'',1,'2021-04-23 11:27:15',NULL),
(26,'2063824643514081319',0,12,'',2,'2021-04-23 11:27:53',NULL),
(27,'1077660647442163826',0,2,'',1,'2021-04-23 11:28:49',NULL),
(28,'7406396430126318513',0,1,'',1,'2021-04-23 11:29:06',NULL),
(29,'5479994012869100411',0,2,'',1,'2021-04-23 11:34:22',NULL),
(30,'256897105008757481',0,13,'',4,'2021-04-23 11:34:38',NULL),
(31,'4203027435384130822',0,2,'',1,'2021-04-23 11:36:43',NULL),
(32,'111729056026828699',0,1,'',1,'2021-04-23 11:37:00',NULL),
(33,'7506402533693907175',0,2,'',1,'2021-04-23 11:45:30',NULL),
(34,'6710904272628951006',0,2,'',1,'2021-04-23 11:45:34',NULL),
(35,'7579802078278736996',0,2,'',1,'2021-04-23 12:29:00',NULL),
(36,'7903157531160955992',0,2,'',1,'2021-04-23 14:06:20',NULL),
(37,'3643477204980796578',0,2,'',1,'2021-04-23 14:06:21',NULL),
(38,'8484830043061977272',0,1,'',1,'2021-04-23 14:10:49',NULL),
(39,'3628849348360890175',0,2,'',1,'2021-04-23 14:12:44',NULL),
(40,'2640230315252622070',0,2,'',1,'2021-04-23 14:12:48',NULL),
(41,'6500514014567607535',0,2,'',1,'2021-04-23 14:13:20',NULL),
(42,'4977624521635687117',0,2,'',1,'2021-04-23 14:13:36',NULL),
(43,'4023561196862941692',0,2,'',1,'2021-04-23 14:15:50',NULL),
(44,'6100436804476519930',0,2,'',1,'2021-04-23 14:15:50',NULL),
(45,'3800346095416514075',0,2,'',1,'2021-04-23 14:16:17',NULL),
(46,'8386842019220962767',0,2,'',1,'2021-04-23 14:17:40',NULL),
(47,'6980184585802248431',0,2,'',1,'2021-04-23 14:20:19',NULL),
(48,'8501484491148896765',0,2,'',1,'2021-04-23 14:20:53',NULL),
(49,'4484030998421680290',0,2,'',1,'2021-04-23 14:23:03',NULL),
(50,'6389576166742553548',0,2,'',1,'2021-04-23 14:23:31',NULL),
(51,'6750691029072194419',0,1,'',1,'2021-04-23 14:37:19',NULL),
(52,'5861494757416212769',0,2,'',1,'2021-04-23 14:39:41',NULL),
(53,'5590762551191455677',0,2,'',1,'2021-04-23 14:43:17',NULL),
(54,'5175352340594465281',0,2,'',1,'2006-01-01 01:06:12',NULL),
(55,'3757304404863531606',0,2,'',1,'2006-01-01 08:33:33',NULL),
(56,'3196949597375281530',0,2,'',1,'2006-01-01 08:34:11',NULL),
(57,'3300270649798727730',0,12,'',2,'2006-01-01 08:48:04',NULL);

/*Table structure for table `token_seq` */

DROP TABLE IF EXISTS `token_seq`;

CREATE TABLE `token_seq` (
  `SEQ_ID` int(11) NOT NULL auto_increment,
  PRIMARY KEY  (`SEQ_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=latin1;

/*Data for the table `token_seq` */

insert  into `token_seq`(`SEQ_ID`) values 
(57);

/*Table structure for table `user_forgotpw_history` */

DROP TABLE IF EXISTS `user_forgotpw_history`;

CREATE TABLE `user_forgotpw_history` (
  `hufh_id` int(11) NOT NULL auto_increment,
  `userid` int(11) default NULL,
  `hufh_sms_send` int(11) default NULL,
  `hufh_raised_at` datetime default NULL,
  `hufh_new_password` varchar(128) default NULL,
  `hufh_reset_at` datetime default NULL,
  `hufh_raised_count` int(11) default '0',
  `hufh_processed` int(11) default NULL,
  KEY `hsh_id` (`hufh_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

/*Data for the table `user_forgotpw_history` */

/*Table structure for table `user_menus` */

DROP TABLE IF EXISTS `user_menus`;

CREATE TABLE `user_menus` (
  `user_menu_sno` bigint(11) NOT NULL auto_increment,
  `roleid` int(11) default '3',
  `userid` int(11) NOT NULL default '1',
  `moduleid` int(11) NOT NULL,
  `created_by` int(11) default '1',
  `created_at` datetime default NULL,
  `modified_by` int(11) default '1',
  `modified_at` datetime default NULL,
  `menu_access` int(11) NOT NULL default '1',
  PRIMARY KEY  (`user_menu_sno`),
  KEY `user_type_key` (`roleid`),
  KEY `user_id_key` (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=latin1;

/*Data for the table `user_menus` */

insert  into `user_menus`(`user_menu_sno`,`roleid`,`userid`,`moduleid`,`created_by`,`created_at`,`modified_by`,`modified_at`,`menu_access`) values 
(1,1,1,1,1,'2019-08-23 14:29:35',1,'2019-08-23 14:29:37',1),
(2,1,1,2,1,'2019-08-23 14:30:42',1,'2019-08-23 14:30:45',0),
(3,1,1,3,1,'2019-08-23 14:30:59',1,'2019-08-23 14:31:01',0),
(4,1,1,4,1,'2019-08-23 14:31:07',1,'2019-08-23 14:31:09',0),
(5,1,1,5,1,'2019-08-23 14:31:14',1,'2019-08-23 14:31:16',0),
(6,1,1,6,1,'2019-08-23 14:31:21',1,'2019-08-23 14:31:23',0),
(7,1,1,7,1,'2019-08-23 14:31:30',1,'2019-08-23 14:31:32',0),
(8,1,1,8,1,'2019-08-23 14:31:38',1,'2019-08-23 14:31:43',0),
(9,1,1,9,1,'2019-08-23 14:31:49',1,'2019-08-23 14:31:51',1),
(10,1,1,10,1,'2019-08-23 14:31:59',1,'2019-08-23 14:32:01',1),
(11,1,1,11,1,'2019-08-23 14:32:07',1,'2019-08-23 14:32:09',0),
(12,1,1,12,1,'2019-08-23 14:32:14',1,'2019-08-23 14:32:16',1),
(13,1,1,13,1,'2019-08-23 14:32:22',1,'2019-08-23 14:32:24',1),
(14,1,1,14,1,'2019-08-23 14:32:32',1,'2019-08-23 14:32:34',1),
(15,1,1,15,1,'2019-08-23 14:32:39',1,'2019-08-23 14:32:41',1),
(16,1,1,16,1,'2019-08-23 14:32:51',1,'2019-08-23 14:32:53',1),
(17,1,1,17,1,'2019-08-23 14:33:00',1,'2019-08-23 14:33:02',1),
(18,1,1,18,1,'2019-08-23 14:33:08',1,'2019-08-23 14:33:09',1),
(19,1,1,19,1,'2019-08-23 14:33:17',1,'2019-08-23 14:33:19',1),
(20,1,1,20,1,'2019-08-23 14:33:26',1,'2019-08-23 14:33:28',1),
(21,2,2,1,1,'2019-08-23 18:22:14',1,'2019-08-23 18:22:17',1),
(22,2,2,6,1,'2019-08-23 18:22:33',1,'2019-08-23 18:22:35',1),
(23,2,2,13,1,'2019-08-23 18:22:54',1,'2019-08-23 18:22:56',1),
(24,2,2,14,1,'2019-08-23 18:23:14',1,'2019-08-23 18:23:16',0),
(25,2,2,15,1,'2019-08-23 18:23:33',1,'2019-08-23 18:23:35',1),
(26,2,2,16,1,'2019-08-23 18:23:54',1,'2019-08-23 18:23:56',1),
(27,2,2,18,1,'2019-08-23 18:24:10',1,'2019-08-23 18:24:12',1),
(28,4,4,1,1,'2019-08-23 18:27:46',1,'2019-08-23 18:27:48',1),
(29,4,4,9,1,'2019-08-23 18:28:01',1,'2019-08-23 18:28:03',1),
(30,4,4,11,1,'2019-08-23 18:28:18',1,'2019-08-23 18:28:20',0),
(31,4,4,12,1,'2019-08-23 18:28:33',1,'2019-08-23 18:28:35',1),
(32,4,4,13,1,'2019-08-23 18:28:48',1,'2019-08-23 18:28:50',1),
(33,4,4,17,1,'2019-08-23 18:29:03',1,'2019-08-23 18:29:05',1),
(34,4,4,18,1,'2019-08-23 18:29:19',1,'2019-08-23 18:29:21',1),
(35,4,4,20,1,'2019-08-23 18:29:35',1,'2019-08-23 18:29:37',1),
(36,1,1,21,1,'2019-08-27 17:06:00',1,'2019-08-27 17:06:02',1),
(37,1,1,22,1,'2019-09-10 15:34:15',1,'2019-09-10 15:34:18',1),
(38,4,1,22,1,'2019-09-10 15:34:36',1,'2019-09-10 15:34:38',1),
(39,1,1,23,1,'2019-09-20 17:51:38',1,'2019-09-20 17:51:40',1),
(40,4,1,21,1,'2019-09-27 12:24:02',1,'2019-09-27 12:24:04',1),
(41,1,1,24,1,'2019-10-09 09:39:00',1,'2019-10-09 09:39:02',1),
(42,1,1,25,1,'2019-10-09 09:44:39',1,'2019-10-09 09:44:41',1),
(43,1,1,26,1,'2019-10-09 09:45:15',1,'2019-10-09 09:45:17',1),
(44,4,1,27,1,'2019-10-15 14:07:03',1,'2019-10-15 14:07:06',1),
(45,3,1,1,1,'2019-11-21 10:08:03',1,'2019-11-21 10:08:06',1),
(46,3,1,2,1,'2019-11-21 10:08:41',1,'2019-11-21 10:08:43',0),
(47,3,1,6,1,'2019-11-21 10:09:27',1,'2019-11-21 10:09:28',0),
(48,3,1,7,1,'2019-11-21 10:09:38',1,'2019-11-21 10:09:39',0),
(49,3,1,8,1,'2019-11-21 10:09:48',1,'2019-11-21 10:09:49',0),
(50,3,1,9,1,'2019-11-21 10:09:56',1,'2019-11-21 10:09:59',1),
(51,3,1,10,1,'2019-11-21 10:10:08',1,'2019-11-21 10:10:10',1),
(52,3,1,11,1,'2019-11-21 10:10:18',1,'2019-11-21 10:10:19',0),
(53,3,1,12,1,'2019-11-21 10:10:28',1,'2019-11-21 10:10:30',1),
(54,3,1,14,1,'2019-11-21 10:10:47',1,'2019-11-21 10:10:48',1),
(55,3,1,15,1,'2019-11-21 10:10:57',1,'2019-11-21 10:10:59',0),
(56,3,1,16,1,'2019-11-21 10:11:30',1,'2019-11-21 10:11:32',0),
(57,3,1,13,1,'2019-11-21 10:10:37',1,'2019-11-21 10:10:39',1),
(58,3,1,17,1,'2019-11-21 10:11:42',1,'2019-11-21 10:11:45',0),
(59,3,1,18,1,'2019-11-21 10:11:55',1,'2019-11-21 10:11:56',1),
(60,3,1,19,1,'2019-11-21 10:12:05',1,'2019-11-21 10:12:07',1),
(61,3,1,20,1,'2019-11-21 10:12:36',1,'2019-11-21 10:12:38',1),
(62,3,1,24,1,'2019-11-21 10:08:52',1,'2019-11-21 10:08:54',1),
(63,3,1,25,1,'2019-11-21 10:09:02',1,'2019-11-21 10:09:03',1),
(64,3,1,26,1,'2019-11-21 10:09:13',1,'2019-11-21 10:09:19',1);

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `userid` int(11) NOT NULL auto_increment,
  `username` varchar(512) default NULL,
  `role` int(11) default NULL,
  `email` varchar(512) default NULL,
  `phone_no` varchar(255) default NULL,
  `ps_number` varchar(512) default NULL,
  `app_ic` varchar(512) default NULL,
  `password` varchar(512) default NULL,
  `user_forgotpw_count` int(11) default '0',
  `user_new_pw` varchar(512) default NULL,
  `ehr_entry_id` int(11) default NULL,
  `dob` date default NULL,
  `city` varchar(512) default NULL,
  `state` varchar(512) default NULL,
  `country` varchar(512) default NULL,
  `status` int(11) default '1',
  `created_at` datetime default NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;

/*Data for the table `users` */

insert  into `users`(`userid`,`username`,`role`,`email`,`phone_no`,`ps_number`,`app_ic`,`password`,`user_forgotpw_count`,`user_new_pw`,`ehr_entry_id`,`dob`,`city`,`state`,`country`,`status`,`created_at`) values 
(1,'superadmin',1,'admin@gmail.com','7338763140','54856486','165468','u8vca2wZVoIAgBNSRURplA==',0,NULL,NULL,'1988-11-14','Nagercoil','TamilNadu','INDIA',1,NULL),
(2,'guest_user',1,'guest@gmail.com','7338763140','54856486','165468','u8vca2wZVoIAgBNSRURplA==',0,NULL,NULL,'1988-11-14','Nagercoil','TamilNadu','INDIA',1,NULL),
(12,'prabha',2,'prabhaindrajith@gmail.com',NULL,'8903367159','1','u8vca2wZVoIAgBNSRURplA==',0,NULL,NULL,NULL,NULL,NULL,NULL,1,'2006-01-01 07:14:57'),
(13,'Ajay',4,'prabha31192@gmail.com',NULL,'8056798887','1','u8vca2wZVoIAgBNSRURplA==',0,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL);

/* Function  structure for function  `FN_OEP_ID_FORMAT` */

/*!50003 DROP FUNCTION IF EXISTS `FN_OEP_ID_FORMAT` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `FN_OEP_ID_FORMAT`(p_txnid INT UNSIGNED,p_current_txnno INT UNSIGNED) RETURNS varchar(24) CHARSET latin1
    DETERMINISTIC
BEGIN
  DECLARE v_TXN_NO  VARCHAR(24);
  DECLARE V_LOOP INT DEFAULT 0;
  DECLARE V_NO_OF_ZEROS INT DEFAULT 0;
  
  SELECT `TXN_NO_OF_ZEROS`,`TXN_START_NAME` INTO V_NO_OF_ZEROS,v_TXN_NO FROM `oep_no_format` WHERE `SEQ_ID` = p_txnid;
  SET v_TXN_NO = CONCAT(v_TXN_NO,'-');
  SET  V_NO_OF_ZEROS = V_NO_OF_ZEROS - LENGTH(p_current_txnno);
  WHILE V_NO_OF_ZEROS > V_LOOP DO
   
    SET v_TXN_NO := CONCAT(v_TXN_NO,0);
   SET V_LOOP = V_LOOP + 1;
  END WHILE;
  RETURN v_TXN_NO;
END */$$
DELIMITER ;

/* Function  structure for function  `FN_OEP_TIME` */

/*!50003 DROP FUNCTION IF EXISTS `FN_OEP_TIME` */;
DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` FUNCTION `FN_OEP_TIME`(p_from_time varchar(24),p_to_time VARCHAR(24)) RETURNS varchar(24) CHARSET latin1
    DETERMINISTIC
BEGIN
  DECLARE v_LASTTWO_FROM  VARCHAR(24);
  DECLARE v_REMAITWO_FROM  VARCHAR(24);
  DECLARE v_HOURS_FROM  int default 0;
  DECLARE v_MINUTES_FROM  INT DEFAULT 0;
  
  DECLARE v_LASTTWO_TO  VARCHAR(24);
  DECLARE v_REMAINTWO_TO  VARCHAR(24);
  DECLARE v_HOURS_TO  INT DEFAULT 0;
  DECLARE v_MINUTES_TO  INT DEFAULT 0;
  
  DECLARE v_DIFF_HOUR  INT DEFAULT 0;
  DECLARE v_DIFF_MINUTE  INT DEFAULT 0;
  
  DECLARE v_OUTPUT  VARCHAR(24);
  
  SELECT RIGHT(p_from_time,2) into v_LASTTWO_FROM;
  SELECT SUBSTRING_INDEX(p_from_time,' ',1) INTO v_REMAITWO_FROM;
  select SUBSTRING_INDEX(v_REMAITWO_FROM, ':', 1) into v_HOURS_FROM;
  SELECT SUBSTRING_INDEX(v_REMAITWO_FROM, ':', -1) INTO v_MINUTES_FROM;
  
  SELECT RIGHT(p_to_time,2) INTO v_LASTTWO_TO;
  SELECT SUBSTRING_INDEX(p_to_time,' ',1) INTO v_REMAINTWO_TO;
  
  SELECT SUBSTRING_INDEX(v_REMAINTWO_TO, ':', 1) INTO v_HOURS_TO;
  SELECT SUBSTRING_INDEX(v_REMAINTWO_TO, ':', -1) INTO v_MINUTES_TO;
  
  
  IF(v_LASTTWO_FROM = "pm") THEN 
  IF (v_HOURS_FROM != 12) THEN
  SET v_HOURS_FROM = v_HOURS_FROM +12 ;
  END IF;
  elseif (v_LASTTWO_FROM = "am") THEN 
  IF (v_HOURS_FROM = 12) THEN
  SET v_HOURS_FROM = 0 ;
  END IF;
  END IF;
  
  IF(v_LASTTWO_TO = "pm") THEN 
  IF (v_HOURS_TO != 12) THEN
  SET v_HOURS_TO = v_HOURS_TO +12 ;
  END IF;
  ELSEIF (v_LASTTWO_TO = "am") THEN 
  IF (v_HOURS_TO = 12) THEN
  SET v_HOURS_TO = 0 ;
  END IF;
  END IF;
  
  
  if(v_HOURS_FROM <= v_HOURS_TO) then
  if(v_MINUTES_FROM <= v_MINUTES_TO) then
  SET v_DIFF_MINUTE = v_MINUTES_TO - v_MINUTES_FROM ;
  else
  SET v_HOURS_TO = v_HOURS_TO - 1;
  insert into `debug_table`(`VALUE1`,`VALUE2`) values (v_HOURS_TO,v_MINUTES_TO);
  set v_MINUTES_TO = v_MINUTES_TO + 60;
  SET v_DIFF_MINUTE = v_MINUTES_TO - v_MINUTES_FROM;
  end if;
  set v_DIFF_HOUR = v_HOURS_TO - v_HOURS_FROM;
  SET v_OUTPUT = CONCAT(v_DIFF_HOUR,":",v_DIFF_MINUTE);
  else 
  set v_OUTPUT = "0";
  end if;
  
  RETURN v_OUTPUT;
END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_ALUMNI_BLOG_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_ALUMNI_BLOG_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_ALUMNI_BLOG_MODULE`(
		IN P_HEADLINE VARCHAR(512),
		IN P_BLOG_ID VARCHAR(512),
		IN P_CONTENT TEXT,
		IN P_CREATED_BY VARCHAR(512),
		IN P_IMAGE VARCHAR(512),
		IN P_DESCRIPTION VARCHAR(512),		
		IN P_STATUS VARCHAR(512),
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
IF P_OPRN = 'INS' THEN
SELECT COUNT(*) INTO V_COUNT FROM `alumni_blog` WHERE `headline` = P_HEADLINE;
IF V_COUNT = 0 THEN
INSERT INTO `alumni_blog`
(
`headline`,`content`,`image`,`description`,`created_at`,`created_by`,`status`)
VALUES(P_HEADLINE,P_CONTENT,P_IMAGE,P_DESCRIPTION,NOW(),P_CREATED_BY,P_STATUS);
SELECT `id` INTO V_FORGOTPW_COUNT FROM `alumni_blog` WHERE `headline` = P_HEADLINE;
SET out_genrate_id = V_FORGOTPW_COUNT;
SET out_result_type = 'S';      
SET out_result_msg = 'Blog details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'HEADLINE  already exists';
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `alumni_blog` WHERE `id` = P_BLOG_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `alumni_blog`
SET    `headline`= P_HEADLINE,
       `content`= P_CONTENT,
       `image`= P_IMAGE,
      `description`= P_DESCRIPTION,
      `created_at`= NOW(),
      `created_by`= P_CREATED_BY,
      `status` = P_STATUS
     WHERE `id` = P_BLOG_ID;
     SET out_genrate_id =P_BLOG_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Blog details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `alumni_blog` WHERE `id` = P_BLOG_ID;
IF V_USER_COUNT = 1 THEN
delete from  `alumni_blog`   WHERE `id` = P_BLOG_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Blog details deleted Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_CONTACT_US` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_CONTACT_US` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_CONTACT_US`(
		IN P_USERNAME VARCHAR(512),
		IN P_USERID VARCHAR(512),
		IN P_EMAIL VARCHAR(512),
		IN P_MOBILE VARCHAR(512),		
		IN P_CONTENT text,		
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
DECLARE V_MAIL VARCHAR(512);
DECLARE V_MAILCONTENT TEXT;
IF P_OPRN = 'INS' THEN
INSERT INTO `contact_us`
(
`name`,`email`,`mobile`,`content`,`created_date`,`created_by`)VALUES
(P_USERNAME,P_EMAIL,P_MOBILE,P_CONTENT,NOW(),P_USERID);
select `email` into V_MAIL FROM `users` WHERE `userid` = 16;
SELECT `email_temp_content` INTO V_MAILCONTENT FROM `email_template` WHERE `email_temp_id` = 4;
SET V_MAILCONTENT = REPLACE(V_MAILCONTENT,'$param1',P_USERNAME);
SET V_MAILCONTENT = REPLACE(V_MAILCONTENT,'$param2',P_EMAIL);
SET V_MAILCONTENT = REPLACE(V_MAILCONTENT,'$param3',P_MOBILE);
SET V_MAILCONTENT = REPLACE(V_MAILCONTENT,'$param4',P_CONTENT);
INSERT INTO `email_history`
(
`imh_email_id`,`imh_email_content`,`imh_email_subject`,`imh_email_created_at`,`imh_staus`)VALUES
(V_MAIL,V_MAILCONTENT,'New Message',now(),0);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Your Queries Added Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_COURSE_MANUAL_REGISTRATION` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_COURSE_MANUAL_REGISTRATION` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_COURSE_MANUAL_REGISTRATION`(
		IN P_ROLE_ID INT,
		IN P_COURSE_ID INT,
		IN P_USER_ID INT,
		IN P_PARTICIPANT_NAME VARCHAR(512),
		IN P_PASSWORD VARCHAR(512),
		IN P_PS_NUMBER VARCHAR(512),
		IN P_EMAIL VARCHAR(512),
		IN P_APPLICABLE_IC INT,
		IN P_JOB_CODE VARCHAR(512),	
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512),
		OUT out_result_sendmail VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_USER_ID INT;
DECLARE V_PARTCIPANT_COUNT INT;    
DECLARE V_PARTCIPANT_ID INT;  
DECLARE  V_ALLOWED_PARTICIPANTS INT;
DECLARE  V_USER_CHECK_COUNT INT;
DECLARE V_APPLIED_PARTICIPANTS INT;
DECLARE V_COURSE_ID_1 INT;
DECLARE V_COURSE_ID_2 INT;
DECLARE V_COURSE_STATUS INT;
DECLARE V_NO_REC_FOUND INTEGER DEFAULT 0;
DECLARE V_MAIL_COUNT INT; 
DECLARE V_CHECK_MAIL VARCHAR(512); 
DECLARE RECV_CURSOR CURSOR FOR
			SELECT  b.`course_id`  FROM  `participants_registration_course_details` a
			JOIN `course_scheduling`c ON c.`cs_id` = a.`course_id`
			JOIN `course_master` b ON b.`course_id` = c.`program_name` 
			JOIN `participants` d ON d.`participant_id`=a.`participant_id`
			WHERE  `email_id` = P_EMAIL GROUP  BY `cs_id`;			
			DECLARE CONTINUE HANDLER 
			FOR NOT FOUND SET V_NO_REC_FOUND = 1;   
			  INSERT INTO `debug_table`(`VALUE4`)VALUES(P_OPRN);    
IF P_OPRN = 'INS' THEN
  INSERT INTO `debug_table`(`VALUE4`)VALUES("INS");
SET V_COURSE_STATUS = 0;
	SELECT COUNT(*) INTO V_USER_COUNT FROM `users` WHERE `email` = P_EMAIL;
	IF V_USER_COUNT = 0 THEN
				 INSERT INTO `debug_table`(`VALUE4`)VALUES('first if');
		SELECT `total_participants_allowed` INTO V_ALLOWED_PARTICIPANTS FROM `course_scheduling`
		WHERE `cs_id` = P_COURSE_ID;
		SELECT COUNT(*) INTO V_APPLIED_PARTICIPANTS FROM `participants_registration_course_details`
		WHERE `course_id` = P_COURSE_ID;		
		IF V_APPLIED_PARTICIPANTS < V_ALLOWED_PARTICIPANTS THEN 
		 INSERT INTO `debug_table`(`VALUE3`)VALUES('second if');		
		        IF P_ROLE_ID != '4'  THEN
		        INSERT INTO `debug_table`(`VALUE4`)VALUES('third if');
				INSERT INTO `users`
				(`username`,`password`,`role`,`email`,`ps_number`,`app_ic`,`status`)VALUES
				(P_PARTICIPANT_NAME,P_PASSWORD,'4',P_EMAIL,P_PS_NUMBER,P_APPLICABLE_IC,'1');
				
				SELECT `userid` INTO V_USER_ID FROM `users` WHERE `email`=P_EMAIL;				
				INSERT INTO `participants`
				(`username`,`email_id`,`jobcode`,`created_date`,`modified_date`,`user_id`)VALUES
				(P_PARTICIPANT_NAME,P_EMAIL,P_JOB_CODE,NOW(),NOW(),V_USER_ID);						
				
				SELECT `participant_id` INTO V_PARTCIPANT_ID FROM `participants` WHERE `email_id` = P_EMAIL;						
				INSERT INTO `participants_registration_course_details`
				(`participant_id`,`course_id` ,`created_date`,`modified_date`,`is_approved`)
				VALUES(V_PARTCIPANT_ID,P_COURSE_ID,NOW(),NOW(),1);
				SET out_genrate_id = 1;
				SET out_result_type = 'S';      
				SET out_result_msg = ' Sucess ';
				END IF;
		ELSE
		                SET out_genrate_id = 0;
				SET out_result_type = 'F';      
				SET out_result_msg = ' Registration closed ';
				END IF;	
				
	ELSE	
	     INSERT INTO `debug_table`(`VALUE3`)VALUES('else ');
			SELECT COUNT(*) INTO V_PARTCIPANT_COUNT FROM `participants` WHERE `email_id` = P_EMAIL;	
			IF V_PARTCIPANT_COUNT > 0 THEN
			INSERT INTO `debug_table`(`VALUE3`)VALUES('else if');
			SELECT `participant_id` INTO V_PARTCIPANT_ID FROM `participants` WHERE `email_id` = P_EMAIL;	
			SELECT `course_id` INTO V_COURSE_ID_2 FROM `course_master` a 
			JOIN `course_scheduling` b ON  b.`program_name` = a.`course_id` WHERE b.`cs_id`= P_COURSE_ID;
				
			OPEN RECV_CURSOR ;
			GET_RECV :
			LOOP
			 FETCH RECV_CURSOR INTO V_COURSE_ID_1;	
			 INSERT INTO `debug_table`(`VALUE2`,`VALUE3`)VALUES(V_COURSE_ID_1,V_COURSE_ID_2);
			 	
			 IF V_COURSE_ID_1 = V_COURSE_ID_2 THEN	
			 INSERT INTO `debug_table`(`VALUE3`)VALUES('cursor if');					
			 SET V_COURSE_STATUS = 1;
			  LEAVE GET_RECV;			
			 END IF;		 
			  IF V_NO_REC_FOUND = 1 THEN 
			  INSERT INTO `debug_table`(`VALUE3`)VALUES('V_NO_REC_FOUND if');	
			 LEAVE GET_RECV;
			 END IF;
			 END LOOP GET_RECV ;
			 CLOSE RECV_CURSOR ;
			 
			      IF V_COURSE_STATUS = 0 THEN
			        INSERT INTO `debug_table`(`VALUE3`)VALUES('V_COURSE_STATUS 0');
			     
			          IF P_ROLE_ID != '4'  THEN
			           INSERT INTO `debug_table`(`VALUE4`)VALUES('P_ROLE_ID');
					  INSERT INTO `participants_registration_course_details`
					 (`participant_id`,`course_id` ,`created_date`,`modified_date`,`is_approved`)
					  VALUES(V_PARTCIPANT_ID,P_COURSE_ID,NOW(),NOW(),1);
					  SET out_genrate_id = 1;
					  SET out_result_type = 'S';      
					  SET out_result_msg = ' Sucess ';
				
		                   ELSE
		                    INSERT INTO `debug_table`(`VALUE4`)VALUES('V_COURSE_STATUS else ');
					INSERT INTO `participants_registration_course_details`
					(`participant_id`,`course_id` ,`created_date`,`modified_date`,`is_approved`)
					VALUES(V_PARTCIPANT_ID,P_COURSE_ID,NOW(),NOW(),0);
					SET out_genrate_id = 1;
					SET out_result_type = 'S';      
					SET out_result_msg = ' Sucess ';
					 END IF;
				ELSE	
		                SET out_genrate_id = 0;
				SET out_result_type = 'F';      
				SET out_result_msg = ' Already Registered ';
			      END IF;				
					
			ELSE
			 INSERT INTO `debug_table`(`VALUE4`)VALUES('invalid  ');
			SET out_genrate_id = 1;
			SET out_result_type = 'F';      
			SET out_result_msg = 'Invalid user';
			END IF;						
END IF;
ELSEIF P_OPRN = 'UPD' THEN
  /*INSERT INTO `debug_table`(`VALUE4`)VALUES("priya");
SELECT COUNT(*) INTO V_USER_COUNT FROM `users` a JOIN `participants` b ON a.`userid`=b.`user_id` 
JOIN `participants_registration_course_details` c ON b.`participant_id`=c.`participant_id`
 WHERE `course_id`=P_COURSE_ID AND a.`email` !=P_EMAIL;
  INSERT INTO `debug_table`(`VALUE4`)VALUES(V_USER_COUNT);
 IF V_USER_COUNT = 0 THEN
 UPDATE `users` SET `username`=P_PARTICIPANT_NAME,`email`=P_EMAIL,`ps_number`=P_PS_NUMBER,`app_ic`=P_APPLICABLE_IC
 WHERE `email` =P_EMAIL;
 UPDATE `participants` SET `username`=P_PARTICIPANT_NAME,`email_id`=P_EMAIL,`jobcode`=P_JOB_CODE where `email_id`=P_EMAIL;*/
 
 SELECT COUNT(*) INTO V_USER_COUNT FROM `users` WHERE `userid`= P_USER_ID;
  SELECT COUNT(*) INTO V_MAIL_COUNT FROM `users` WHERE `email`= P_EMAIL;
  
  IF V_USER_COUNT = 1 AND  V_MAIL_COUNT = 0 THEN
  
  UPDATE `users` SET `username`=P_PARTICIPANT_NAME,`email`=P_EMAIL,`ps_number`=P_PS_NUMBER,
  `app_ic`=P_APPLICABLE_IC ,`password` = P_PASSWORD   WHERE `userid` = P_USER_ID ;
  
   SELECT COUNT(*) INTO V_USER_COUNT FROM `participants` WHERE `user_id`= P_USER_ID;
   IF V_USER_COUNT = 1 THEN  
  UPDATE `participants` SET `email_id` = P_EMAIL ,`contact_no` = P_PS_NUMBER,`username`= P_PARTICIPANT_NAME WHERE `user_id` =P_USER_ID ;
   END IF;
   
    SELECT `email` INTO V_CHECK_MAIL FROM `users` WHERE `userid`= P_USER_ID;
    IF V_CHECK_MAIL = P_EMAIL THEN  
    SET out_result_sendmail = 'no';
    else
     SET out_result_sendmail = 'yes';
     END IF;
			SET out_genrate_id = 1;
			SET out_result_type = 'S';      
			SET out_result_msg = 'Updated Successfully';
			SET out_result_sendmail = 'yes';
ELSE	
			SET out_genrate_id = 0;
			SET out_result_type = 'F';      
			 SET out_result_sendmail = 'no';
			SET out_result_msg = 'Mail id exists';		
 END IF;
ELSEIF P_OPRN = 'UPD_PASSWORD' THEN
		UPDATE `users` SET `password`=P_PASSWORD WHERE `email`=P_EMAIL;
ELSEIF P_OPRN = 'APPROVED' THEN
		UPDATE `participants_registration_course_details` SET `is_approved`='1' WHERE `prcd_id`=P_COURSE_ID;
		SET out_genrate_id = 1;
		SET out_result_type = 'S';      
		SET out_result_msg = 'Approved Successfully';
END IF;
END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_COURSE_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_COURSE_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_COURSE_MODULE`(
		IN P_COURSE_NAME VARCHAR(255),
		IN P_COURSE_DESC TEXT,
		IN P_CREATED_BY VARCHAR(24),
		IN P_MODIFIED_BY VARCHAR(24),
		IN P_COURSE_ID VARCHAR(24),
		IN P_DURATION INT,
		IN P_APPLICABLE_IC int,
		IN P_STATUS VARCHAR(24),
		IN P_COURSE_DETAILS VARCHAR(1024),
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_COUNT INT;
declare V_COURSEID int ;
DECLARE V_SUB_ID INT ;
DECLARE V_FORGOTPW_COUNT INT;
/*DECLARE V_NO_REC_FOUND INTEGER DEFAULT 0;
DECLARE RECV_CURSOR CURSOR FOR
SELECT COALESCE(`sub_id`,0) FROM  `subject_master` WHERE `course_id` = P_COURSE_ID;
 DECLARE CONTINUE HANDLER 
        FOR NOT FOUND SET V_NO_REC_FOUND = 1;*/
        
IF P_OPRN = 'INS' THEN
SELECT COUNT(*) INTO V_COUNT FROM `course_master` WHERE `course_name` = P_COURSE_NAME;
if V_COUNT = 0 then
INSERT INTO `course_master`
(
`course_name`,
`course_desc`,
`course_details`,
`duration`,
`applicable_ic`,
`created_date`,
`modified_date`,
`created_by`,
`modified_by`,
`status`
)VALUES
(
P_COURSE_NAME,
P_COURSE_DESC,
P_COURSE_DETAILS,
P_DURATION,
P_APPLICABLE_IC,
NOW(),
NOW(),
P_CREATED_BY,
P_MODIFIED_BY,
P_STATUS
);
select `course_id` into V_COURSEID from `course_master` where `course_name` = P_COURSE_NAME;
SET out_genrate_id = V_COURSEID;
SET out_result_type = 'S';      
SET out_result_msg = 'Course Details Saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Course Name Already Exists';
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `course_master` WHERE `course_id` = P_COURSE_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `course_master`
   SET `course_name` = P_COURSE_NAME,
       `course_desc`= P_COURSE_DESC,
       `course_details`= P_COURSE_DETAILS,
       `duration` = P_DURATION,
       `applicable_ic` = P_APPLICABLE_IC,
       `created_by`= P_CREATED_BY,
       `modified_by` = P_MODIFIED_BY,
       `created_date`= NOW(),
       `modified_date` = NOW(),
       `status` = P_STATUS
     WHERE `course_id` = P_COURSE_ID;
     
    
/*OPEN RECV_CURSOR ;
GET_RECV :
LOOP
FETCH RECV_CURSOR INTO V_SUB_ID;
UPDATE `subject_master` SET `status` = P_STATUS WHERE `sub_id` = V_SUB_ID;
IF V_NO_REC_FOUND = 1 THEN 
 LEAVE GET_RECV;
 END IF;
END LOOP GET_RECV ;
  CLOSE RECV_CURSOR ;*/
 SELECT `course_id` INTO V_COURSEID FROM `course_master` WHERE `course_name` = P_COURSE_NAME;
SET out_genrate_id = V_COURSEID;
SET out_result_type = 'S';      
SET out_result_msg = 'Course details updated successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `course_master` WHERE `course_id` = P_COURSE_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `course_master`
   SET `status` = 0  WHERE `course_id` = P_COURSE_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Course details deleted successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_COURSE_SCHEDULING_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_COURSE_SCHEDULING_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_COURSE_SCHEDULING_MODULE`(
		IN P_PROGRAM_NAME VARCHAR(48),
		IN P_FACULTY_NAME VARCHAR(24),
		IN P_TOTAL_PARTICIPANTS VARCHAR(48),
		IN P_START_HOUR VARCHAR(512),
		IN P_END_HOUR VARCHAR(512),		
		IN P_START_MINUTE VARCHAR(512),
		IN P_END_MINUTE VARCHAR(512),
		IN P_START_FORMAT VARCHAR(512),
		IN P_END_FORMAT VARCHAR(512),
		IN P_START_DATE DATE,
		IN P_APPLICABLE_IC VARCHAR(24),
		IN P_END_DATE DATE,
		IN P_CS_ID VARCHAR(24),
		IN P_OPRN VARCHAR(24),
		IN P_STATUS VARCHAR(48),
		IN P_HOST TEXT,
		OUT out_genrate_id VARCHAR(48),
		OUT out_genrate_mail_id VARCHAR(48),
		OUT out_genrate_mail_content VARCHAR(512),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
DECLARE V_MAIL_CONTENT VARCHAR (512);
DECLARE V_COURSENAME VARCHAR (512);
DECLARE V_STARTTIME VARCHAR (512);
DECLARE V_ENDTIME VARCHAR (512);
DECLARE V_FACULTYMAIL VARCHAR (512);
DECLARE V_SCHEDULE_START_FORMAT VARCHAR (512);
DECLARE V_SCHEUDLE_COUNT INT;
DECLARE V_IMH_ID  VARCHAR(48);
declare V_MAIL_SUBJECT VARCHAR(128);
declare V_COURSE_SCHEDULE_ID int;
declare V_COURSE_NAME VARCHAR (512);
IF P_OPRN = 'INS' THEN
SET V_STARTTIME = CONCAT(P_START_HOUR,':',P_START_MINUTE,' ',P_START_FORMAT);
SET V_ENDTIME = CONCAT(P_END_HOUR,':',P_END_MINUTE,' ',P_END_FORMAT);
SELECT COALESCE(MAX(`cs_id`)+1,1) INTO V_FORGOTPW_COUNT  FROM `course_scheduling`;
SELECT `FN_OEP_ID_FORMAT`(8,V_FORGOTPW_COUNT) INTO V_SCHEDULE_START_FORMAT;
SET V_SCHEDULE_START_FORMAT = CONCAT(V_SCHEDULE_START_FORMAT,V_FORGOTPW_COUNT);
SELECT COUNT(*) INTO  V_SCHEUDLE_COUNT  FROM `course_scheduling`  WHERE  `program_name`=P_PROGRAM_NAME AND `start_date`=P_START_DATE AND
`end_date`= P_END_DATE  AND  `start_time`=V_STARTTIME AND `end_time`= V_ENDTIME AND `faculty_name`=P_FACULTY_NAME;
IF V_SCHEUDLE_COUNT = 0 THEN
INSERT INTO `course_scheduling`(
`program_name`,
`schedule_name`,
`start_date`,
`end_date`,
`start_time`,
`end_time`,
`applicable_ic`,
`total_participants_allowed`,
`faculty_name`,
`status`,
`created_at`
)VALUES
(
P_PROGRAM_NAME,
V_SCHEDULE_START_FORMAT,
P_START_DATE,
P_END_DATE,
V_STARTTIME,
V_ENDTIME,
P_APPLICABLE_IC,
P_TOTAL_PARTICIPANTS,
P_FACULTY_NAME,
P_STATUS,
now() 
);
SELECT `course_name` INTO V_COURSENAME FROM `course_master` WHERE `course_id` = P_PROGRAM_NAME;
SELECT `email` INTO V_FACULTYMAIL  FROM `faculty_master` WHERE `faculty_id` = P_FACULTY_NAME;
SELECT `email_temp_content`,`email_temp_subject` INTO V_MAIL_CONTENT, V_MAIL_SUBJECT FROM `email_template` WHERE `email_temp_id` = 2;
select `cs_id` into V_COURSE_SCHEDULE_ID FROM `course_scheduling` ORDER BY `cs_id` DESC LIMIT 1;
SELECT `course_name` INTO V_COURSE_NAME FROM `course_master` where `course_id`=P_PROGRAM_NAME;
/*SET V_MAIL_CONTENT = REPLACE(REPLACE(V_MAIL_CONTENT,'$param1',V_COURSENAME),
					'$param2',concat(P_HOST,'/#/login?schid=',V_COURSE_SCHEDULE_ID,'&crsname=',CONCAT(V_COURSE_NAME,'-',V_SCHEDULE_START_FORMAT)));*/
SET V_MAIL_CONTENT = REPLACE(REPLACE(V_MAIL_CONTENT,'$param1',V_COURSENAME),
					'$param2',concat(P_HOST,'/#/login'));					
SELECT MAX(COALESCE(`imh_id`,0))+1  INTO V_IMH_ID FROM `email_history`;
INSERT INTO `email_history`
(`imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content`,`imh_email_created_at`,`imh_staus`,`imh_retry_count`)
VALUES
(V_IMH_ID,V_FACULTYMAIL,V_MAIL_SUBJECT,V_MAIL_CONTENT,NOW(),0,0);
SET out_genrate_id = 1;
set out_genrate_mail_id = V_IMH_ID;
SET out_genrate_mail_content = V_MAIL_CONTENT;
SET out_result_type = 'S';      
SET out_result_msg = 'Schedule details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Already Exist';
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
SET V_STARTTIME = CONCAT(P_START_HOUR,':',P_START_MINUTE,' ',P_START_FORMAT);
SET V_ENDTIME = CONCAT(P_END_HOUR,':',P_END_MINUTE,' ',P_END_FORMAT);
SELECT COUNT(*) INTO V_USER_COUNT FROM `course_scheduling` WHERE `cs_id` = P_CS_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `course_scheduling`
SET `program_name` = P_PROGRAM_NAME,
     `start_date`= P_START_DATE,
    `end_date` = P_END_DATE ,
     `start_time`= V_STARTTIME,
     `end_time`= V_ENDTIME,
    `applicable_ic`= P_APPLICABLE_IC,
    `faculty_name`= P_FACULTY_NAME,
    `total_participants_allowed` = P_TOTAL_PARTICIPANTS,
    `status` = P_STATUS
     WHERE `cs_id` = P_CS_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Schedule details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
IF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `course_scheduling` WHERE `cs_id` = P_CS_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `course_scheduling`
   SET `status` = 0  WHERE `cs_id` = P_CS_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Schedule details DELETED Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_COURSE_YEAR` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_COURSE_YEAR` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_COURSE_YEAR`(
		IN P_YEAR_ID INT,
		IN P_COURSE_ID INT,
		IN P_YEAR_NAME VARCHAR(24),
		IN P_COURSE_NAME VARCHAR(24),
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
IF P_OPRN = 'INS' THEN
INSERT INTO `year_master`
(
`year_name`,`course_id`,`course_name`,`created_at`)VALUES
(P_YEAR_NAME,P_COURSE_ID,P_COURSE_NAME,NOW());
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `year_master` WHERE `year_id` = P_YEAR_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `year_master`
   SET `year_name` = P_YEAR_NAME,
       `P_COURSE_ID`= P_COURSE_ID,
       `created_at`= NOW()      
     WHERE `year_id` = P_YEAR_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `year_master` WHERE `year_id` = P_YEAR_ID;
IF V_USER_COUNT = 1 THEN
DELETE FROM `year_master`  WHERE `year_id` = P_YEAR_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Details DELETED Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_EXAM_MASTER` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_EXAM_MASTER` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_EXAM_MASTER`(
		IN P_EXAM_ID VARCHAR(48),
		IN P_SUBJECT_ID VARCHAR(24),
		IN P_DURATION_FROM VARCHAR(24),
		IN P_DURATION_TO VARCHAR(24),
		IN P_QUESTION_ID VARCHAR(24),
		IN P_ATTEND VARCHAR(24),
		IN P_TEST_ID INT,
		IN P_EXAM_DATE VARCHAR(24),
		IN P_MARK VARCHAR(24),
		in P_BATCH varchar(48),
		IN P_PARTICIPANT_ID INT,
		IN P_TEST_NAME VARCHAR(48),
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
IF P_OPRN = 'INS' THEN
INSERT INTO `exam_master`
(
`exam_id`,`sub_id`,`question_master_id`,`exam_date`,`duration_from`,`duration_to`)VALUES
(P_EXAM_ID,P_SUBJECT_ID,P_QUESTION_ID,NOW(),P_DURATION_FROM,P_DURATION_TO);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Exam details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `exam_master` WHERE `exam_id` = P_EXAM_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `exam_master`
   SET `sub_id` = P_SUBJECT_ID,
       `question_master_id`= P_QUESTION_ID,
       `exam_date`= NOW(),
       `duration_from`= P_DURATION_FROM,
       `duration_to` = P_DURATION_TO         
     WHERE `exam_id` = P_EXAM_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Exam details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `exam_master` WHERE `exam_id` = P_EXAM_ID;
IF V_USER_COUNT = 1 THEN
DELETE FROM `exam_master`  WHERE `exam_id` = P_EXAM_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Exam details DELETED Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
IF P_OPRN = 'SCH-TEST' THEN
update `exam_master`
set
`batch`= P_BATCH,
`participant_id`= P_PARTICIPANT_ID,
`test_name`= P_TEST_NAME,
`test_id`=P_TEST_ID
where `exam_id` = P_EXAM_ID;
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'TEST details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_FACULTY_EDUCATION_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_FACULTY_EDUCATION_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_FACULTY_EDUCATION_DETAILS`(
                IN P_FACULTY_ID INT,
		IN P_QUALIFICATION VARCHAR(512),
		IN P_COURSE VARCHAR(512),
		IN P_SPECIFICATION VARCHAR(512),
		IN P_UNIVERSITY VARCHAR(512),
		IN P_START_DATE VARCHAR(512),
		IN P_END_DATE VARCHAR(48),
		IN P_OPRN VARCHAR(24)
		
    )
BEGIN
IF P_OPRN = 'INS' THEN
INSERT INTO `faculty_master_education_details`
(
`faculty_id`,
`qualification`,
`course`,
`specification`,
`university`,
`start_date`,
`end_date`,
`created_date`,
`modified_date`
)VALUES
(
P_FACULTY_ID,
P_QUALIFICATION,
P_COURSE,
P_SPECIFICATION,
P_UNIVERSITY,
P_START_DATE,
P_END_DATE,
NOW(),
NOW()
);
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_FACULTY_EXPERIENCE_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_FACULTY_EXPERIENCE_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_FACULTY_EXPERIENCE_DETAILS`(
        IN P_FACULTY_ID INT,
		IN P_DESIGNATION VARCHAR(512),
		IN P_COMPANY_NAME VARCHAR(512),
		IN P_JOB_DESC VARCHAR(512),
		IN P_START_DATE VARCHAR(512),
		IN P_END_DATE VARCHAR(48),
		IN P_OPRN VARCHAR(24)
		
    )
BEGIN
IF P_OPRN = 'INS' THEN
INSERT INTO `faculty_master_experience_details`
(
`faculty_id`,
`designation`,
`company_name`,
`job_desc`,
`start_date`,
`end_date`,
`created_date`,
`modified_date`
)VALUES
(
P_FACULTY_ID,
P_DESIGNATION,
P_COMPANY_NAME,
P_JOB_DESC,
P_START_DATE,
P_END_DATE,
NOW(),
NOW()
);
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_FACULTY_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_FACULTY_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_FACULTY_MODULE`(
                IN P_FACULTY_ID INT(11),
		IN P_FIRSTNAME VARCHAR(512),		
		IN P_MIDDDLE_NAME VARCHAR(512),
		IN P_DESCRIPTION TEXT,
		IN P_APPLICABLE_IC VARCHAR(512),		
		IN P_LASTNAME VARCHAR(512),
		IN P_USERNAME VARCHAR(512),
		IN P_PASSWORD VARCHAR(512),
		IN P_EMAIL VARCHAR(512),
		IN P_GENDER VARCHAR(512),
		IN P_DOB VARCHAR(512),
		IN P_CONTACT_NO VARCHAR(512),
		IN P_QUALIFICATION VARCHAR(512),
		IN P_OCCUPATION VARCHAR(512),
		IN P_EXPERIENCE VARCHAR(512),
		IN P_MAIN_SUBJECT VARCHAR(512),
		IN P_STATUS VARCHAR(512),
		IN P_OPRN VARCHAR(512),
		IN P_REFNO VARCHAR(512),
		IN P_FACULTY_PROFILE VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(512),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
DECLARE V_EMAIL_COUNT INT;
DECLARE V_FACULTY_ID INT;
IF P_OPRN = 'INS' THEN
INSERT INTO `faculty_master`
(`faculty_firstname`,`faculty_middlename`,`faculty_lastname`,`username`,`password`,`email`,
`description`,`applicable_ic`,`contact_no`,`gender`,`dob`,`qualification`,`occupation`,`experience`,
`main_subject`,`created_date`,`modified_date`,`status`,`ref_no`)VALUES
(P_FIRSTNAME,P_MIDDDLE_NAME,P_LASTNAME,P_USERNAME,P_PASSWORD,P_EMAIL,P_DESCRIPTION,
P_APPLICABLE_IC,P_CONTACT_NO,P_GENDER,P_DOB,P_QUALIFICATION,P_OCCUPATION,P_EXPERIENCE,
P_MAIN_SUBJECT,NOW(),NOW(),P_STATUS,P_REFNO);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Faculty details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `faculty_master` WHERE `userid` = P_FACULTY_ID;
IF V_USER_COUNT = 1 THEN
SELECT COUNT(*) INTO V_EMAIL_COUNT FROM `faculty_master` WHERE `email`=P_EMAIL AND `userid` != P_FACULTY_ID;
IF V_EMAIL_COUNT = 0 THEN 
UPDATE `faculty_master`
SET `faculty_firstname` = P_FIRSTNAME,
`faculty_middlename`= P_LASTNAME,
    `faculty_lastname`= P_LASTNAME,
     `username` = P_USERNAME,
     `password` = P_PASSWORD,
     `email`= P_EMAIL,
     `description`= P_DESCRIPTION,
     `applicable_ic`= P_APPLICABLE_IC,
     `contact_no` = P_CONTACT_NO ,
     `faculty_profile`=P_FACULTY_PROFILE,
     `gender` = P_GENDER,
     `dob`= P_DOB,
     `qualification` = P_QUALIFICATION ,
      `occupation`= P_OCCUPATION,
     `experience` = P_EXPERIENCE ,
     `main_subject` = P_MAIN_SUBJECT,
     `created_date`= NOW(),
     `modified_date`= NOW(),
     `status` = P_STATUS ,
     `ref_no` = P_REFNO     
     WHERE `userid` = P_FACULTY_ID;
     SELECT `faculty_id` INTO V_FACULTY_ID FROM `faculty_master` WHERE `userid` = P_FACULTY_ID;
SET out_genrate_id = P_FACULTY_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Faculty details updated Successfully';
ELSE 
SET out_result_type = 'F';      
SET out_result_msg = 'Already Exist';
END IF;
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `faculty_master` WHERE `faculty_id` = P_FACULTY_ID;
IF V_USER_COUNT = 1 THEN
DELETE FROM `faculty_master`  WHERE `faculty_id` = P_FACULTY_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Faculty details DELETED Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
IF P_OPRN = 'IMGUPD' THEN
INSERT INTO `debug_table`(`VALUE1`)VALUES('s');
UPDATE `faculty_master` SET `faculty_profile`=P_FACULTY_PROFILE
WHERE `userid` = P_FACULTY_ID;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_FACULTY_SKILLS_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_FACULTY_SKILLS_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_FACULTY_SKILLS_DETAILS`(
                 IN P_FACULTY_ID INT,
		IN P_SKILLS VARCHAR(512),
		IN P_EFFICIENCY VARCHAR(512),
		IN P_OPRN VARCHAR(24)
		
    )
BEGIN
IF P_OPRN = 'INS' THEN
INSERT INTO `faculty_master_skills_details`
(
`faculty_id`,
`skill`,
`efficiency`,
`created_date`,
`modified_date`
)VALUES
(
P_FACULTY_ID,
P_SKILLS,
P_EFFICIENCY,
NOW(),
NOW()
);
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_IMPORT_PARTICIPANT_REGISTRATION` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_IMPORT_PARTICIPANT_REGISTRATION` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_IMPORT_PARTICIPANT_REGISTRATION`(
IN P_CELL1 INT,
IN P_CELL2 VARCHAR(128),/*QUESTION*/
IN P_CELL3 VARCHAR(128),
IN P_CELL4 VARCHAR(128),/*OPTIONS*/
IN P_CELL5 VARCHAR(128),
IN P_CELL6 VARCHAR(128),
IN P_CELL7 INT,/*FILESEQ*/
IN P_CELL8 INT,/*USERID*/
IN P_CELL9 INT,/*SCHEDULE ID*/
IN P_INDEX INT
)
BEGIN
DECLARE V_SEQ_COUNT INT;
DECLARE V_USER_ID INT;
DECLARE out_genrate_id VARCHAR(48);
DECLARE out_result_type VARCHAR(48);
DECLARE o_out_msg VARCHAR(128);
DECLARE V_IMH_SEQNO INT;
DECLARE V_PARTCIPANT_COUNT INT;    
DECLARE V_PARTCIPANT_ID INT;  
DECLARE  V_ALLOWED_PARTICIPANTS INT;
DECLARE V_APPLIED_PARTICIPANTS INT;
SELECT COUNT(*) INTO V_SEQ_COUNT   FROM `participants` a JOIN `users` b 
LEFT JOIN `participants_registration_course_details` c ON 
a.`participant_id`=c.`participant_id` AND c.`course_id`=P_CELL9 WHERE a.`email_id` = P_CELL4 AND b.`email`=P_CELL4;
IF V_SEQ_COUNT = 0 THEN
SELECT `total_participants_allowed` INTO V_ALLOWED_PARTICIPANTS FROM `course_scheduling`
WHERE `cs_id` = P_CELL9;
SELECT COUNT(*) INTO V_APPLIED_PARTICIPANTS FROM `participants_registration_course_details`
WHERE `course_id` = P_CELL9;
IF V_APPLIED_PARTICIPANTS <= V_ALLOWED_PARTICIPANTS THEN 
INSERT INTO `users`
(
`username`,
`role`,
`email`,
`ps_number`,
`app_ic`,
`status`,
`created_at`
)VALUES
(
P_CELL2,
'4',
P_CELL4,
P_CELL3,
P_CELL5,
'1',
now()
);
SELECT `userid` INTO V_USER_ID FROM `users` WHERE `email`=P_CELL4;
SELECT COUNT(*) INTO V_PARTCIPANT_COUNT FROM `participants` WHERE `email_id` = P_CELL4;
IF V_PARTCIPANT_COUNT = 0 THEN 
INSERT INTO `participants`
(
`username`,
`email_id`,
`created_date`,
`modified_date`,
`user_id`
)VALUES
(
P_CELL2,
P_CELL4,
NOW(),
NOW(),
V_USER_ID
);
END IF;
SELECT `participant_id` INTO V_PARTCIPANT_ID FROM `participants` WHERE `email_id` = P_CELL4;
INSERT INTO `participants_registration_course_details`
(
`participant_id`,
`course_id` ,
`created_date`,
`modified_date`,
`is_approved`
)
VALUES
(
V_PARTCIPANT_ID,
P_CELL9,
NOW(),
NOW(),
1
);
SET out_result_type = "Success";		     
SET o_out_msg = 'Paticipant created Successfully';
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Registration closed';
END IF; 
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Paticipant Already Exists';
END IF; 
INSERT INTO `oep_question_import_status` (`seq_no`,`status`,`error_msg`,`uploaded_by`,`record_seq_no`)
VALUES(P_CELL7,out_result_type,o_out_msg,P_CELL8,(P_INDEX + 1));
END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_IMPORT_QUESTION` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_IMPORT_QUESTION` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_IMPORT_QUESTION`(
IN P_CELL1 VARCHAR(512),
IN P_CELL2 TEXT,/*QUESTION*/
IN P_CELL3 VARCHAR(512),/*QUESTION TYPE*/
IN P_CELL4 TEXT,/*OPTIONS*/
IN P_CELL5 TEXT,/*OPTIONS*/
IN P_CELL6 TEXT,/*OPTIONS*/
IN P_CELL7 TEXT,/*OPTIONS*/
IN P_CELL8 TEXT,/*ANSWER*/
IN P_CELL9 VARCHAR(512),/*IMAGE*/
IN P_CELL10 varchar(512),/*MARK*/
IN P_CELL11 INT,/* FILESEQ*/
IN P_CELL12 INT,/* USERID*/
IN P_CELL13 INT,/* QUESTIONID*/
IN P_INDEX INT
)
BEGIN
DECLARE V_SEQ_COUNT INT;
DECLARE V_QUESTION_ID INT;
DECLARE out_genrate_id VARCHAR(48);
DECLARE out_result_type VARCHAR(48);
DECLARE o_out_msg VARCHAR(128);
DECLARE V_FAILCOUNT INT;
SELECT COUNT(*) INTO V_SEQ_COUNT FROM `question_details` WHERE `question` = P_CELL2 and `ques_master_id`=P_CELL13;
IF P_CELL8 != '-' THEN
IF P_CELL7 != '-' THEN
IF P_CELL6 != '-' THEN
IF P_CELL5 != '-' THEN
IF P_CELL4 != '-' THEN
IF P_CELL2 != '-' THEN
if P_CELL1 != '-' THEN
if (P_CELL3 = 'Single-Select' or P_CELL3 = 'Multi-Select') then
if P_CELL10 != '-' then
IF V_SEQ_COUNT = 0 THEN
INSERT INTO `question_details`(`ques_master_id`,`question`,`question_type`,`option_1`,`option_2`,`option_3`,`option_4`,`answer`,`image`,`mark`,`created_date`,`created_by`,`status`)
VALUES(P_CELL13,P_CELL2,P_CELL3,P_CELL4,P_CELL5,P_CELL6,P_CELL7,P_CELL8,P_CELL9,P_CELL10,NOW(),P_CELL12,1);
SET out_result_type = "Success";		     
SET o_out_msg = 'Question Created Successfully';
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Question Already Exists';
END IF; 
else
SET out_result_type = "Fail";
SET o_out_msg = 'Please enter question mark';
END IF; 
else
SET out_result_type = "Fail";
SET o_out_msg = 'Please select valid question type';
END IF;
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Please enter S.no';
END IF;
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Please enter Question';
END IF;
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Please enter Option1';
END IF;
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Please enter Option2';
END IF;
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Please enter Option3';
END IF;
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Please enter Option4';
END IF;
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Please enter correct answer';
END IF;
SELECT `question_id` INTO V_QUESTION_ID FROM`question_details` WHERE `question`= P_CELL2 AND `ques_master_id` = P_CELL13;
INSERT INTO `oep_question_import_status` (`seq_no`,`status`,`question_id`,`error_msg`,`uploaded_by`,`record_seq_no`,`serialno`)
VALUES(P_CELL11,out_result_type,V_QUESTION_ID,o_out_msg,P_CELL12,P_INDEX,P_CELL1);
select count(*) into V_FAILCOUNT from `oep_question_import_status` where `status`='Success' and `seq_no`=P_CELL11;
IF V_FAILCOUNT =0 THEN 
DELETE FROM `question_master` WHERE `id` = P_CELL13;
END IF;
END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_INSERT_EMAIL_HISTORY` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_INSERT_EMAIL_HISTORY` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_INSERT_EMAIL_HISTORY`(
	IN P_IMH_ID INT,
	IN P_IMH_MAIL_ID VARCHAR(256),
	IN P_IMH_MAIL_SUBJECT VARCHAR(256),
	IN P_IMH_MAIL_CONTENT TEXT,
	IN P_IMH_STATUS INT,
	IN P_IMH_RESPONSE VARCHAR(256),
	IN P_OPRN VARCHAR(64),
		
	OUT out_genrate_id int,
	OUT out_result_type VARCHAR(1),
	OUT out_result_msg VARCHAR(128) 
	)
BEGIN
DECLARE V_IMH_SEQNO INT;
IF P_OPRN = "INS" THEN
	IF((SELECT COUNT(*) FROM `email_history`) = 0) THEN
		SET V_IMH_SEQNO = 1;
		INSERT INTO `email_history` 
		(`imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content`,`imh_staus`,`imh_email_created_at`,`imh_retry_count`) VALUES
		(V_IMH_SEQNO,P_IMH_MAIL_ID,P_IMH_MAIL_SUBJECT,P_IMH_MAIL_CONTENT,P_IMH_STATUS,NOW(),0);		
				SET out_genrate_id = V_IMH_SEQNO;      
				SET out_result_type = 'S';      
				SET out_result_msg = 'Inserted Successfully';
				
	ELSE
		select MAX(`imh_id`)+1 into V_IMH_SEQNO from `email_history`;
		INSERT INTO `email_history` 
		(`imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content`,`imh_staus`,`imh_email_created_at`,`imh_retry_count`) VALUES
		(V_IMH_SEQNO,P_IMH_MAIL_ID,P_IMH_MAIL_SUBJECT,P_IMH_MAIL_CONTENT,P_IMH_STATUS,NOW(),0);		
				SET out_genrate_id = V_IMH_SEQNO;      
				SET out_result_type = 'S';      
				SET out_result_msg = 'Inserted Successfully';
	END IF;
ELSEIF P_OPRN = "UPD_EMAIL_STATUS" THEN
	IF P_IMH_STATUS = 1 THEN
	UPDATE `email_history` SET `imh_staus` = P_IMH_STATUS,`imh_response` = P_IMH_RESPONSE WHERE `imh_id` = P_IMH_ID;
	ELSE
	UPDATE `email_history` SET `imh_staus` = P_IMH_STATUS,`imh_retry_count` = (`imh_retry_count` + 1),
	`imh_response` = P_IMH_RESPONSE
	 WHERE `imh_id` = P_IMH_ID;
	END IF;
END IF;	
	
END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_NEWLOGIN_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_NEWLOGIN_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_NEWLOGIN_MODULE`(
		IN P_USERID VARCHAR(48),
		IN P_OLD_PASSWORD VARCHAR(24),		
		IN P_NEW_PASSWORD VARCHAR(24),
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
DECLARE V_IMH_ID  VARCHAR(512);
DECLARE V_USERID INT;
DECLARE V_MOBNO VARCHAR(24);
DECLARE V_EMAIL VARCHAR(48);
DECLARE V_SMS_CONTENT TEXT;
declare V_SMS_SUBJECT varchar(256);
DECLARE V_REGISTERED_COUNT INT;
IF P_OPRN = 'CHANGE_PW' THEN
IF((SELECT `password` FROM `users` WHERE `userid` = P_USERID) = P_OLD_PASSWORD) THEN
UPDATE `users` SET `password` = P_NEW_PASSWORD WHERE `userid` = P_USERID;
SET out_genrate_id = P_USERID;
SET out_result_type = 'S';      
SET out_result_msg = 'Password Changed Successfully.Please Re-Login to continue';
ELSE
SET out_genrate_id = P_USERID;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid OldPassword';
END IF;
ELSEIF P_OPRN = 'FORGOT_PW' THEN
	SELECT COUNT(*) INTO V_USER_COUNT FROM `users` WHERE `email` = P_USERID;
	IF V_USER_COUNT = 0 THEN
	SET out_result_type = 'F';      
	SET out_result_msg = 'Invalid Userid';
	ELSE
	/* Add Coalesce by Ram 
	SELECT COALESCE(`user_forgotpw_count`,0),`userid`,`phone_no`,`email` INTO V_FORGOTPW_COUNT,V_USERID,V_MOBNO,V_EMAIL FROM `users` WHERE `email` = P_USERID;
	IF V_FORGOTPW_COUNT <= 0 THEN
	SELECT COUNT(*) INTO V_REGISTERED_COUNT FROM `user_forgotpw_history` WHERE `userid` = V_USERID AND `hufh_processed` = 0;
	IF V_REGISTERED_COUNT = 0 THEN
	INSERT INTO `user_forgotpw_history`
	(`userid`,`hufh_new_password`,`hufh_sms_send`,`hufh_raised_at`,`hufh_raised_count`,`hufh_processed`)
	VALUES
	(V_USERID,P_OLD_PASSWORD,0,NOW(),(`hufh_raised_count` + 1),0);
	ELSE
	UPDATE `users` SET `user_new_pw`=P_NEW_PASSWORD WHERE `userid` = V_USERID ;
	END IF;
	Added by Ram */
	SELECT COALESCE(`user_forgotpw_count`,0),`userid`,`phone_no`,`email` INTO V_FORGOTPW_COUNT,V_USERID,V_MOBNO,V_EMAIL FROM `users` WHERE `email` = P_USERID;
	UPDATE `users` SET `password`=P_NEW_PASSWORD,`user_new_pw` = P_OLD_PASSWORD,
	`user_forgotpw_count` = (`user_forgotpw_count` - 1) WHERE `userid` = V_USERID;
	/*ELSE*/
	SELECT COUNT(*) INTO V_REGISTERED_COUNT FROM `email_history` WHERE `imh_staus` = 0 AND `imh_email_id`= V_EMAIL;
	IF V_REGISTERED_COUNT = 0 THEN
	/*
	SELECT `sms_message_content` INTO V_SMS_CONTENT FROM `sms_template` WHERE `sms_temp_id` = 6;
	SET V_SMS_CONTENT = REPLACE(V_SMS_CONTENT,'$param1',P_OLD_PASSWORD);
	INSERT INTO `sms_history`
	(`ish_mob_no`,`ish_sms_content`,`ish_created_by`,`ish_created_at`,`ish_status`,`ish_retry_count`)
	VALUES
	(V_MOBNO,V_SMS_CONTENT,V_USERID,NOW(),0,0);*/
	SELECT `email_temp_content`,`email_temp_subject` INTO V_SMS_CONTENT, V_SMS_SUBJECT  FROM `email_template` WHERE `email_temp_id` = 1;
	SET V_SMS_CONTENT = REPLACE(V_SMS_CONTENT,'$param1',P_OLD_PASSWORD);
	SELECT MAX(coalesce(`imh_id`,0))+1  INTO V_IMH_ID FROM`email_history`;
	INSERT INTO `email_history`
	(`imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content`,`imh_email_created_at`,`imh_staus`,`imh_retry_count`)
	VALUES
	(V_IMH_ID,V_EMAIL,V_SMS_SUBJECT,V_SMS_CONTENT,NOW(),0,0);
	UPDATE `users` SET `password`=P_NEW_PASSWORD,`user_new_pw` = P_NEW_PASSWORD WHERE `userid` = V_USERID;
	SET out_genrate_id = V_IMH_ID;
	SET out_result_type = 'S';
	SET out_result_msg = "SMS Receive Shortly";
	ELSE
	SET out_result_type = 'F';
	SET out_result_msg = 'Already Request raised and still under processing. Please wait...';
	END IF;
	END IF;
	/*END IF;*/
ELSEIF P_OPRN = 'RESET_PW' THEN
UPDATE `user_forgotpw_history` SET `hufh_processed` = 1 WHERE `hufh_id` = P_USERID;
SET out_result_type = 'S';
SET out_result_msg = 'Password Reset Successfully';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_NEWS_AND_EVENTS_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_NEWS_AND_EVENTS_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_NEWS_AND_EVENTS_MODULE`(
		IN P_HEADLINE VARCHAR(512),
		IN P_BLOG_ID VARCHAR(512),
		IN P_CONTENT TEXT,
		IN P_CREATED_BY VARCHAR(512),
		IN P_IMAGE VARCHAR(512),
		IN P_DESCRIPTION VARCHAR(512),		
		IN P_STATUS VARCHAR(512),
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
IF P_OPRN = 'INS' THEN
SELECT COUNT(*) INTO V_COUNT FROM `news_blog` WHERE `headline` = P_HEADLINE;
IF V_COUNT = 0 THEN
INSERT INTO `news_blog`
(
`headline`,`content`,`image`,`description`,`created_at`,`created_by`,`status`)
VALUES(P_HEADLINE,P_CONTENT,P_IMAGE,P_DESCRIPTION,NOW(),P_CREATED_BY,P_STATUS);
SELECT `id` INTO V_FORGOTPW_COUNT FROM `news_blog` WHERE `headline` = P_HEADLINE;
SET out_genrate_id = V_FORGOTPW_COUNT;
SET out_result_type = 'S';      
SET out_result_msg = 'News and Event details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'HEADLINE  already exists';
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `news_blog` WHERE `id` = P_BLOG_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `news_blog`
SET    `headline`= P_HEADLINE,
       `content`= P_CONTENT,
       `image`= P_IMAGE,
      `description`= P_DESCRIPTION,
      `created_at`= NOW(),
      `created_by`= P_CREATED_BY,
      `status` = P_STATUS
     WHERE `id` = P_BLOG_ID;
     SET out_genrate_id =P_BLOG_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'News and Event details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `news_blog` WHERE `id` = P_BLOG_ID;
IF V_USER_COUNT = 1 THEN
delete from  `news_blog`   WHERE `id` = P_BLOG_ID;
  SET out_genrate_id =P_BLOG_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'News and Event details deleted Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_PARTCIPANT_EDUCATION_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_PARTCIPANT_EDUCATION_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_PARTCIPANT_EDUCATION_DETAILS`(
        IN P_PARTCIPANT_ID INT,
		IN P_QUALIFICATION VARCHAR(512),
		IN P_COURSE VARCHAR(512),
		IN P_SPECIFICATION VARCHAR(512),
		IN P_UNIVERSITY VARCHAR(512),
		IN P_START_DATE VARCHAR(512),
		IN P_END_DATE VARCHAR(48),
		IN P_OPRN VARCHAR(24)
		
    )
BEGIN
IF P_OPRN = 'INS' THEN
INSERT INTO `partcipant_master_education_details`
(
`participant_id`,
`qualification`,
`course`,
`specification`,
`university`,
`start_date`,
`end_date`,
`created_date`,
`modified_date`
)VALUES
(
P_PARTCIPANT_ID,
P_QUALIFICATION,
P_COURSE,
P_SPECIFICATION,
P_UNIVERSITY,
P_START_DATE,
P_END_DATE,
NOW(),
NOW()
);
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_PARTCIPANT_SKILLS_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_PARTCIPANT_SKILLS_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_PARTCIPANT_SKILLS_DETAILS`(
        IN P_PARTCIPANT_ID INT,
		IN P_SKILLS VARCHAR(512),
		
		IN P_OPRN VARCHAR(24)
		
    )
BEGIN
IF P_OPRN = 'INS' THEN
INSERT INTO `partcipant_master_skills_details`
(
`participant_id`,
`skill`,
`created_date`,
`modified_date`
)VALUES
(
P_PARTCIPANT_ID,
P_SKILLS,
NOW(),
NOW()
);
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_PARTICIPANT_CERTIFICATE_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_PARTICIPANT_CERTIFICATE_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_PARTICIPANT_CERTIFICATE_DETAILS`(
		IN P_PARTICIPANT_ID INT,
		IN P_BATCH VARCHAR(512),
		IN P_RANDOM_NO INT,
		IN P_TS_ID INT,
		iN P_OPRN VARCHAR(24)
		/*IN P_TEST_ID VARCHAR(512),		
		IN P_TEST_SCHEDULE_ID INT,
		IN P_QUESTION_ID INT,
		
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  */
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
DECLARE V_TEST_SCHEDULE_ID VARCHAR(512);
DECLARE V_QUESTION_ID VARCHAR(512);
IF P_OPRN = 'INS' THEN
SELECT `test_schedule_id`,`ques_master_id` INTO V_TEST_SCHEDULE_ID,V_QUESTION_ID FROM `test_schedule`
WHERE `id` = P_TS_ID;
INSERT INTO `part_certificate_dtls`
/*(
`part_id`,`test_id`,`batch`,`ts_id`,`ques_master_id`,`random_no`)VALUES
(P_PARTICIPANT_ID,P_TEST_ID,P_BATCH,P_TEST_SCHEDULE_ID,P_QUESTION_ID,P_RANDOM_NO);*/
(
`part_id`,`batch`,`test_id`,`ques_master_id`,`ts_id`,`random_no`)VALUES
(P_PARTICIPANT_ID,P_BATCH,V_TEST_SCHEDULE_ID,V_QUESTION_ID,P_TS_ID,P_RANDOM_NO);
/*SET out_genrate_id = P_RANDOM_NO;
SET out_result_type = 'S';      
SET out_result_msg = 'Details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';*/
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_PARTICIPANT_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_PARTICIPANT_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_PARTICIPANT_MODULE`(
		IN P_FIRSTNAME VARCHAR(48),
		IN P_LASTNAME VARCHAR(24),
		IN P_USERNAME VARCHAR(48),
		IN P_PASSWORD VARCHAR(24),
		IN P_MIDDLE_NAME VARCHAR(512),
		IN P_DEPARTMENT VARCHAR(512),
		IN P_DESCRIPTION TEXT,
		IN P_APPLICABLE_IC VARCHAR(512),
		IN P_EMAIL VARCHAR(48),
		IN P_GENDER VARCHAR(24),
		IN P_CITY VARCHAR(512),
		IN P_ADDRESS VARCHAR(512),
		IN P_IMAGEPATH VARCHAR(512),
		IN P_PINCODE VARCHAR(512),
		in P_EMPLOYEE_ID VARCHAR(512),
		IN P_DOB DATE,
		IN P_REG_NO VARCHAR(48),
		IN P_USERID INT,
		IN P_YEAR_ID INT,
		IN P_REG_TYPE VARCHAR(48),
		/*IN P_CREATED_DATE DATE,
		IN P_MODIFIED_DATE DATE,*/
		IN P_COURSE_ID INT,
		IN P_OPRN VARCHAR(24),
		IN P_REFNO VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_question_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_PARTID INT;
DECLARE V_FORGOTPW_COUNT VARCHAR(512)  ;
/*
SELECT `FN_OEP_ID_FORMAT`(5,P_REFNO) INTO V_FORGOTPW_COUNT;
SET out_question_id = CONCAT(V_FORGOTPW_COUNT,P_REFNO);*/
 
IF P_OPRN = 'INS' THEN
INSERT INTO `participants`
(
`user_id`,`ref_no`,`reg_no`,`reg_type`,`first_name`,`middle_name`,`last_name`,`username`,`password`,`department`,`employee_id`,`email_id`,`gender`,`imagepath`,
`dob`,`description`,`applicable_ic`,`course_id`,`year_id`,`contact_no`,`address`,`city`,`pincode`,`created_date`,`modified_date`)VALUES
(P_USERID,P_REFNO,P_REG_NO,P_REG_TYPE,P_FIRSTNAME,P_MIDDLE_NAME,P_LASTNAME,P_USERNAME,P_PASSWORD,P_DEPARTMENT,P_EMPLOYEE_ID,P_EMAIL,P_GENDER,P_IMAGEPATH,
P_DOB,P_DESCRIPTION,P_APPLICABLE_IC,P_COURSE_ID,P_YEAR_ID,P_REG_NO,P_ADDRESS,P_CITY,P_PINCODE,NOW(),NOW());
SELECT `participant_id` INTO V_PARTID FROM `participants` WHERE `user_id` = P_USERID;
SET out_genrate_id = V_PARTID;
SET out_result_type = 'S';      
SET out_result_msg = 'Participant details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
IF P_OPRN = 'UPD' THEN
SELECT `participant_id` INTO V_PARTID FROM `participants` WHERE `user_id` = P_USERID;
SELECT COUNT(*) INTO V_USER_COUNT FROM `participants` WHERE `user_id` = P_USERID;
IF V_USER_COUNT = 1 THEN
UPDATE `participants`
SET `first_name` = P_FIRSTNAME,
    `last_name`= P_LASTNAME,
     `username` = P_USERNAME,
     `password` = P_PASSWORD,
     `department` = P_DEPARTMENT,
     `employee_id`= P_EMPLOYEE_ID,
     `email_id`= P_EMAIL,     
      `middle_name` = P_MIDDLE_NAME,
     `description` = P_DESCRIPTION,
     `applicable_ic`= P_APPLICABLE_IC, 
      `reg_no` = P_REG_NO ,
     `reg_type` = P_REG_TYPE,
     `address`= P_ADDRESS,
     `imagepath`=P_IMAGEPATH,
     `city`= P_CITY,
     `pincode`= P_PINCODE,
     `contact_no`= P_REG_NO,
     `gender` = P_GENDER,
     `dob`= P_DOB,     
      `year_id`= P_YEAR_ID,        
     `created_date`= NOW(),
     `modified_date`= NOW(),
     `ref_no` = P_REFNO     
     WHERE `user_id` = P_USERID;
     SET out_genrate_id = V_PARTID;
SET out_result_type = 'S';      
SET out_result_msg = 'Participant details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `participants` WHERE `user_id` = P_USERID;
IF V_USER_COUNT = 1 THEN
DELETE FROM `participants`  WHERE `user_id` = P_USERID;
SET out_result_type = 'S';      
SET out_result_msg = 'Participant details DELETED Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_QUESTION_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_QUESTION_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_QUESTION_DETAILS`(
		IN P_QUESTION_NAME VARCHAR(1024),
		IN P_SUBJECT_ID INT(11),
		IN P_QUESTION_ID INT(11),
		IN P_CREATED_BY INT(11),
		IN P_MODIFIED_BY INT(11),
		IN P_CREATED_DATE DATE,
		IN P_MODIFIED_DATE DATE,
		IN P_OPTION1 VARCHAR(1024),
		IN P_OPTION2 VARCHAR(1024),
		IN P_OPTION3 VARCHAR(1024),
		IN P_OPTION4 VARCHAR(1024),
		IN P_ANSWER VARCHAR(1024),
		IN P_MAIN_SUBJECT VARCHAR(24),
		IN P_STATUS INT(11),
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
IF P_OPRN = 'INS' THEN
    
    INSERT INTO `question_details`
    (`question_id`,`sub_id`,`question`,`option_1`,`option_2`,`option_3`,`option_4`,
    `answer`,`created_date`,`modified_date`,`created_by`,`modified_by`,`main_subject`,`status`)VALUES
    (P_QUESTION_ID,P_SUBJECT_ID,P_QUESTION_NAME,P_OPTION1,P_OPTION2,P_OPTION3,P_OPTION4,P_ANSWER,
    NOW(),NOW(),1,1,P_MAIN_SUBJECT,P_STATUS);
    
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'QUESTION details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `question_details` WHERE `question_id` = P_QUESTION_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `question_details`
SET `sub_id` = P_SUBJECT_ID,
     `question`= P_QUESTION_NAME,
      `main_subject`= P_MAIN_SUBJECT,
      `created_date`= NOW() 
     WHERE `question_id` = P_QUESTION_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'QUESTION details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `question_details` WHERE `question_id` = P_QUESTION_ID;
IF V_USER_COUNT = 1 THEN
DELETE FROM `question_details`  WHERE `question_id` = P_QUESTION_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'QUESTION details DELETED Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_QUESTION_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_QUESTION_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_QUESTION_MODULE`(
		IN P_TESTNAME VARCHAR(512),
		IN P_SUBJECT_ID INT(11),
		IN P_TEST_ID VARCHAR(512),
		IN P_BATCH VARCHAR(512),
		IN P_CREATED_BY INT(11),
		IN P_STATUS INT(11),
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_ID INT;
DECLARE V_USER_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
IF P_OPRN = 'INS' THEN
    
    INSERT INTO `question_master`
    (`test_id`,`test_name`,`sub_id`,`batch`,`created_date`,`created_by`,`status`)VALUES
    (P_TEST_ID,P_TESTNAME,P_SUBJECT_ID,P_BATCH,NOW(),1,P_STATUS);
    
    SELECT `id` INTO V_ID  FROM  `question_master` WHERE `test_id` = P_TEST_ID AND `sub_id`= P_SUBJECT_ID;
SET out_genrate_id = V_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Question Bank saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `question_master` WHERE `test_id` = P_TEST_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `question_master`
SET `sub_id` = P_SUBJECT_ID,
     `batch` = P_BATCH,
     `test_name`= P_TESTNAME,
      `status`= P_STATUS,
      `created_date`= NOW() 
     WHERE `test_id` = P_TEST_ID;
     SELECT `id` INTO V_ID  FROM  `question_master` WHERE `test_id` = P_TEST_ID ;
 SET out_genrate_id = V_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Question Bank updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `question_master` WHERE `test_id` = P_TEST_ID;
IF V_USER_COUNT = 1 THEN
DELETE FROM `question_master`  WHERE `test_id` = P_TEST_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Question Bank deleted Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_BLOG_COMMENTS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_BLOG_COMMENTS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_BLOG_COMMENTS`(
                IN P_BLOG_ID INT,
		IN P_COMMENTS TEXT,
		IN P_CREATED_BY INT,
		IN P_OPRN VARCHAR(24),
		IN P_STATUS INT,
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
		
		
    )
BEGIN
IF P_OPRN = 'INS' THEN
INSERT INTO `alumni_blog_comments`
(
`comments`,
`created_at`,
`created_by`,
`status`,
`blog_id`
)VALUES
(
P_COMMENTS,
NOW(),
P_CREATED_BY,
P_STATUS,
P_BLOG_ID
);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Comments saved Successfully';
ELSEIF P_OPRN = 'NEWS_INS' THEN
INSERT INTO `news_events_blog_comments`
(
`comments`,
`created_at`,
`created_by`,
`status`,
`news_blog_id`
)VALUES
(
P_COMMENTS,
NOW(),
P_CREATED_BY,
P_STATUS,
P_BLOG_ID
);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Comments saved Successfully';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_BLOG_LIKES` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_BLOG_LIKES` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_BLOG_LIKES`(
        IN P_COMMENT_ID INT,
		IN P_CREATED_BY INT,
		IN P_OPRN VARCHAR(24),
		IN P_STATUS INT,
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
		
		
    )
BEGIN
DECLARE V_LIKE_COUNT INT;
IF P_OPRN = 'INS' THEN
INSERT INTO `alumni_blog_likes`
(
`comment_id`,
`likes_count`,
`created_at`,
`created_by`,
`status`
)VALUES
(
P_COMMENT_ID,
'1',
now(),
P_CREATED_BY,
P_STATUS
);
 SELECT COALESCE(SUM(`likes_count`),0) INTO V_LIKE_COUNT FROM `alumni_blog_likes` WHERE `comment_id`=P_COMMENT_ID;
SET out_genrate_id = V_LIKE_COUNT;
SET out_result_type = 'S';      
SET out_result_msg = 'likes saved Successfully';
elseIF P_OPRN = 'NEWS_INS' THEN
INSERT INTO `news_events_blog_likes`
(
`comment_id`,
`likes_count`,
`created_at`,
`created_by`,
`status`
)VALUES
(
P_COMMENT_ID,
'1',
NOW(),
P_CREATED_BY,
P_STATUS
);
 SELECT COALESCE(SUM(`likes_count`),0) INTO V_LIKE_COUNT FROM `alumni_blog_likes` WHERE `comment_id`=P_COMMENT_ID;
SET out_genrate_id = V_LIKE_COUNT;
SET out_result_type = 'S';      
SET out_result_msg = 'likes saved Successfully';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_BLOG_REPLY` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_BLOG_REPLY` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_BLOG_REPLY`(
        IN P_COMMENT_ID INT,
		IN P_REPLY_CONTENT TEXT,
		IN P_CREATED_BY INT,
		IN P_OPRN VARCHAR(24),
		IN P_STATUS INT,
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
		
		
    )
BEGIN
IF P_OPRN = 'INS' THEN
INSERT INTO `alumni_blog_reply`
(
`comment_id`,
`created_at`,
`created_by`,
`status`,
`reply_content`
)VALUES
(
P_COMMENT_ID,
NOW(),
P_CREATED_BY,
P_STATUS,
P_REPLY_CONTENT
);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Reply saved Successfully';
elseIF P_OPRN = 'NEWS_INS' THEN
INSERT INTO `news_events_blog_reply`
(
`comment_id`,
`created_at`,
`created_by`,
`status`,
`reply_content`
)VALUES
(
P_COMMENT_ID,
NOW(),
P_CREATED_BY,
P_STATUS,
P_REPLY_CONTENT
);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Reply saved Successfully';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_EMAILTEMPLATE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_EMAILTEMPLATE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_EMAILTEMPLATE`(
		IN P_TEMPLATENAME VARCHAR(512),
		IN P_TEMPLATE_ID VARCHAR(512),
		IN P_SUBJECT VARCHAR(512),
		IN P_CONTENT VARCHAR(512),		
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
IF P_OPRN = 'INS' THEN
SELECT COUNT(*) INTO V_COUNT FROM `email_template` WHERE `email_temp_name` = P_TEMPLATENAME;
IF V_COUNT = 0 THEN
INSERT INTO `email_template`
(
`email_temp_name`,`email_temp_subject`,`email_temp_content`)VALUES
(
P_TEMPLATENAME,P_SUBJECT,P_CONTENT
);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Template details saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Template name already exists';
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `email_template` WHERE `email_temp_id` = P_TEMPLATE_ID;
IF V_USER_COUNT = 1 THEN
UPDATE `email_template`
SET `email_temp_name`= P_TEMPLATENAME,
`email_temp_subject`= P_SUBJECT,
`email_temp_content` = P_CONTENT
     WHERE `email_temp_id` = P_TEMPLATE_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Template details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `email_template` WHERE `email_temp_id` = P_TEMPLATE_ID;
IF V_USER_COUNT = 1 THEN
delete from  `email_template`   WHERE `email_temp_id` = P_TEMPLATE_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Template details deleted Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_GALLERY_IMAGE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_GALLERY_IMAGE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_GALLERY_IMAGE`(
                IN P_CATEGORY_ID INT,
		IN P_IMAGE_PATH VARCHAR(512),
		IN P_IMAGE_DESC TEXT,
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512) 	
		
    )
BEGIN
DECLARE V_COUNT INT;
IF P_OPRN = 'INS' THEN
INSERT INTO `gallery_image_category_details`
(
`category_id`,
`category_image_path`,
`category_image_desc`
)VALUES
(
P_CATEGORY_ID,
P_IMAGE_PATH,
P_IMAGE_DESC
);
else
SELECT COUNT(*) INTO V_COUNT FROM `gallery_image_category_details` WHERE `category_image_id` = P_CATEGORY_ID;
IF V_COUNT = 1 THEN
delete from `gallery_image_category_details` where `category_image_id` = P_CATEGORY_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Image details deleted Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid';
END IF;
end if;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_QUESTION_IMAGES` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_QUESTION_IMAGES` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_QUESTION_IMAGES`(
IN P_QUESTION_ID INT,
IN P_IMAGEPATH VARCHAR(512),
IN P_OPRN VARCHAR(256),
OUT out_genrate_id VARCHAR(48),
OUT out_result_type VARCHAR(1),
OUT out_result_msg VARCHAR(48)
)
BEGIN
DECLARE V_SEQ_COUNT INT;
DECLARE V_TOKEN_COUNT INT;
IF P_OPRN = 'UPD' THEN
SET out_genrate_id = P_QUESTION_ID;
insert into `debug_table`(`VALUE1`)values("fgfd");
       UPDATE `question_details` SET 
       `image` = P_IMAGEPATH
       WHERE `question_id` = P_QUESTION_ID;     
       
SET out_result_type = 'S';      
SET out_result_msg = 'Details saved Successfully';
ELSE
SET out_result_type = 'F';      
SET out_result_msg = 'IMAGE not Available. Try again!!';
END IF;
UPDATE `oep_question_import_status` SET `error_msg`= out_result_msg where `question_id` = P_QUESTION_ID;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_TEST_ADMINISTRATOR` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_TEST_ADMINISTRATOR` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_TEST_ADMINISTRATOR`(
		IN P_TEST_STARTED VARCHAR(512),
		IN P_TEST_DATE DATE,
		IN P_QUESTION_ID VARCHAR(512),
		IN P_TS_ID VARCHAR(512),
		IN P_START_HOUR VARCHAR(512),
		IN P_DURATION_HOUR VARCHAR(512),
		IN P_START_FORMAT VARCHAR(512),
		IN P_DURATION_MINUTE VARCHAR(512),
		IN P_START_MINUTE VARCHAR(512),
		IN P_BATCH VARCHAR(512),
		IN P_CREATED_BY INT(11),
		IN P_ADMINISTRATED_BY INT(11),
		IN P_STATUS INT(11),
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_ID INT;
DECLARE V_USER_COUNT INT;
DECLARE V_CHECK_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
DECLARE V_SCHEDULE_START_FORMAT VARCHAR (512);
DECLARE V_STARTTIME VARCHAR (512);
DECLARE V_ENDTIME VARCHAR (512);
DECLARE V_END_HOUR_COUNT VARCHAR (512);
DECLARE V_END_HOUR VARCHAR (512);
DECLARE V_END_MINUTE VARCHAR (512);
DECLARE V_END_FORMAT VARCHAR (512);
DECLARE V_TOTAL_QUESTIONS INT DEFAULT 0;
IF P_OPRN = 'INS' THEN
  SET V_END_HOUR_COUNT = 0;
SELECT COUNT(*) INTO V_CHECK_COUNT FROM `test_schedule` WHERE `ques_master_id` = P_QUESTION_ID AND `batch` = P_BATCH;
IF (V_CHECK_COUNT = 0  ) THEN 
SET V_STARTTIME = CONCAT(P_START_HOUR,':',P_START_MINUTE,' ',P_START_FORMAT);
SET V_END_HOUR = P_START_HOUR + P_DURATION_HOUR;
IF (P_START_FORMAT = 'am' AND V_END_HOUR > 12) THEN
SET V_END_HOUR = V_END_HOUR -12;
 END IF;
SET V_END_MINUTE = P_START_MINUTE + P_DURATION_MINUTE;
IF (V_END_MINUTE > 59) THEN
  SET V_END_HOUR_COUNT = 1;
  SET V_END_MINUTE = 60 - V_END_MINUTE;
 /* SET V_END_HOUR = V_END_HOUR+ V_END_HOUR_COUNT;*/
 END IF;
 
  IF V_END_HOUR = 12  THEN
  SET V_END_HOUR =  V_END_HOUR_COUNT;
  ELSE
  SET V_END_HOUR = V_END_HOUR+ V_END_HOUR_COUNT;
   END IF;
  
  IF (P_START_FORMAT = 'am' AND V_END_HOUR < 12) THEN
  SET V_END_FORMAT = 'am';
    ELSE
  SET V_END_FORMAT = 'pm';
   END IF;
   
   SET V_ENDTIME = CONCAT(V_END_HOUR,':',V_END_MINUTE,' ',V_END_FORMAT);
  
SELECT COALESCE(MAX(`id`)+1,1) INTO V_FORGOTPW_COUNT  FROM `test_schedule`;
SELECT `FN_OEP_ID_FORMAT`(9,V_FORGOTPW_COUNT) INTO V_SCHEDULE_START_FORMAT;
SET V_SCHEDULE_START_FORMAT = CONCAT(V_SCHEDULE_START_FORMAT,V_FORGOTPW_COUNT);
SELECT COUNT(*) INTO V_USER_COUNT FROM  `test_schedule` WHERE `ques_master_id` = P_QUESTION_ID AND `testdate` = P_TEST_DATE  
    AND `batch` = P_BATCH;
    IF V_USER_COUNT = 0 THEN
    
    INSERT INTO `test_schedule`
    (`ques_master_id`,`is_test_started`,`test_schedule_id`,`batch`,`testdate`,`start_time`,`end_time`,
    `created_by`,`created_at`,`administrated_by`,`administrated_at`,`status`)VALUES
    (P_QUESTION_ID,P_TEST_STARTED,V_SCHEDULE_START_FORMAT,P_BATCH,P_TEST_DATE,V_STARTTIME,V_ENDTIME,
     P_CREATED_BY,NOW(),P_ADMINISTRATED_BY,NOW(),P_STATUS);
    
    SELECT `id` INTO V_ID  FROM  `test_schedule` WHERE `ques_master_id` = P_QUESTION_ID AND `testdate` = P_TEST_DATE  
    AND `batch` = P_BATCH;
SET out_genrate_id = V_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Test Schedule saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Test Schedule Exists';
END IF;
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Test Schedule already Exists';
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
SET V_STARTTIME = CONCAT(P_START_HOUR,':',P_START_MINUTE,' ',P_START_FORMAT);
  SET V_END_HOUR_COUNT = 0;
INSERT INTO `debug_table`(`VALUE1`)VALUES(V_STARTTIME);
SET V_END_HOUR = P_START_HOUR + P_DURATION_HOUR;
SET V_END_MINUTE = P_START_MINUTE + P_DURATION_MINUTE;
IF (P_START_FORMAT = 'am' AND V_END_HOUR > 12) THEN
SET V_END_HOUR = V_END_HOUR -12;
 END IF;
IF (V_END_MINUTE > 59) THEN
  SET V_END_HOUR_COUNT = 1;
  SET V_END_MINUTE = 60 - V_END_MINUTE; 
 END IF;
 
 
 IF V_END_HOUR = 12  THEN
  SET V_END_HOUR = V_END_HOUR+ V_END_HOUR_COUNT;
  ELSE
  SET V_END_HOUR = V_END_HOUR+ V_END_HOUR_COUNT;
   END IF;
   
   
  IF (P_START_FORMAT = 'am' AND V_END_HOUR < 12) THEN
  SET V_END_FORMAT = 'am';
  ELSE
  SET V_END_FORMAT = 'pm';
   END IF;
   
   SET V_ENDTIME = CONCAT(V_END_HOUR,':',V_END_MINUTE,' ',V_END_FORMAT);
   INSERT INTO `debug_table`(`VALUE3`)VALUES(V_ENDTIME);
SELECT COUNT(*) INTO V_USER_COUNT FROM `test_schedule` WHERE `id` = P_TS_ID;
IF V_USER_COUNT = 1 THEN
SELECT COALESCE(COUNT(*),0) INTO V_TOTAL_QUESTIONS FROM `test_schedule` a JOIN 
`question_details` b ON a.`ques_master_id` = b.`ques_master_id`  WHERE a.`id` = P_TS_ID;
UPDATE `test_schedule`
SET 
     `batch` = P_BATCH,
      `ques_master_id` = P_QUESTION_ID,
     `is_test_started`= P_TEST_STARTED,
     `testdate` = P_TEST_DATE,
     `start_time`= V_STARTTIME,
     `end_time`= V_ENDTIME,
      `status`= P_STATUS,
      `administrated_by`= P_ADMINISTRATED_BY,
      /*`created_at`=NOW(), */
      `administrated_at`= NOW(),
      /*`created_by`= P_CREATED_BY,*/
      `total_questions` = V_TOTAL_QUESTIONS 
     WHERE `id` = P_TS_ID; 
     
SET out_result_type = 'S';      
SET out_result_msg = 'Test Started Successfully';
ELSE
SET out_genrate_id = P_QUESTION_ID;
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `test_schedule` WHERE `id` = P_TS_ID;
IF V_USER_COUNT = 1 THEN
DELETE FROM `test_schedule`  WHERE `id` = P_TS_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Test Schedule deleted Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_TEST_PARTICIPANT_QUESTION_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_TEST_PARTICIPANT_QUESTION_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_TEST_PARTICIPANT_QUESTION_DETAILS`(
		IN P_SCHEDULE_ID INT(11),
		IN P_PARTICIPANT_ID INT(11),
		IN P_QUESTION_ID INT(11),
		IN P_QUESTION_ANSWER TEXT,
		IN P_END_TIME VARCHAR(512),
		IN P_OPRN VARCHAR(24)
		/*OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)*/  
    )
BEGIN
DECLARE V_TOTAL_MARK INT;
DECLARE V_PARTICIPANT_TEST_ID INT; 
declare V_ANSWER TEXT;
declare V_MARK int;
DECLARE V_ATTEMPTED INT;
DECLARE P_MARK INT;
IF P_OPRN = 'INS' THEN
	
    SELECT coalesce(`id`,0) into V_PARTICIPANT_TEST_ID from `test_participants` 
    where `ts_id` = P_SCHEDULE_ID and `userid` = P_PARTICIPANT_ID;
    
    SELECT `answer`,`mark` into V_ANSWER,V_MARK FROM `question_details` a 
    JOIN `test_schedule` b ON  a.`ques_master_id` = b.`ques_master_id` 
    WHERE `question_id` = P_QUESTION_ID AND b.`id` = P_SCHEDULE_ID;
    
    if LENGTH(P_QUESTION_ANSWER) > 0 then
    SET V_ATTEMPTED = 1;
    else
    SET V_ATTEMPTED = 0;
    end if;
    
    if TRIM(V_ANSWER) = TRIM(P_QUESTION_ANSWER) then
    set P_MARK = V_MARK;
    else
    SET P_MARK = 0;
    end if;
    INSERT INTO `part_ques_details`
    (`part_test_id`,`ind_ques_id`,`ind_ques_ans`,`ind_ques_mark`,`ind_ques_attempted`)VALUES
    (V_PARTICIPANT_TEST_ID,P_QUESTION_ID,P_QUESTION_ANSWER,P_MARK,V_ATTEMPTED);
    
    
    /*SELECT SUM(`ind_ques_mark`) INTO V_TOTAL_MARK  FROM  `part_ques_details`
     WHERE `part_test_id` = P_P_PARTICIPANT_TEST_ID ;
	UPDATE `test_participants` SET `total_mark` = V_TOTAL_MARK,`end_time`=P_END_TIME WHERE `id` = P_P_PARTICIPANT_TEST_ID; */
	
    END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SAVE_TEST_SCHEDULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SAVE_TEST_SCHEDULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SAVE_TEST_SCHEDULE`(
		IN P_TEST_STARTED VARCHAR(512),
		IN P_TEST_DATE DATE,
		IN P_QUESTION_ID VARCHAR(512),
		IN P_TS_ID VARCHAR(512),
		IN P_START_HOUR VARCHAR(512),
		IN P_DURATION_HOUR VARCHAR(512),
		IN P_START_FORMAT VARCHAR(512),
		IN P_DURATION_MINUTE VARCHAR(512),
		IN P_START_MINUTE VARCHAR(512),
		IN P_BATCH VARCHAR(512),
		IN P_CREATED_BY INT(11),
		IN P_ADMINISTRATED_BY INT(11),
		IN P_STATUS INT(11),
		IN P_OPRN VARCHAR(24),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_ID INT;
DECLARE V_USER_COUNT INT;
DECLARE V_CHECK_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
DECLARE V_SCHEDULE_START_FORMAT VARCHAR (512);
DECLARE V_STARTTIME VARCHAR (512);
DECLARE V_ENDTIME VARCHAR (512);
DECLARE V_END_HOUR_COUNT VARCHAR (512);
DECLARE V_END_HOUR VARCHAR (512);
DECLARE V_END_MINUTE VARCHAR (512);
DECLARE V_END_FORMAT VARCHAR (512);
DECLARE V_TOTAL_QUESTIONS INT DEFAULT 0;
IF P_OPRN = 'INS' THEN
  SET V_END_HOUR_COUNT = 0;
SELECT COUNT(*) INTO V_CHECK_COUNT FROM `test_schedule` WHERE `ques_master_id` = P_QUESTION_ID AND `batch` = P_BATCH;
IF (V_CHECK_COUNT = 0  ) THEN 
SET V_STARTTIME = CONCAT(P_START_HOUR,':',P_START_MINUTE,' ',P_START_FORMAT);
SET V_END_HOUR = P_START_HOUR + P_DURATION_HOUR;
IF (P_START_FORMAT = 'am' AND V_END_HOUR > 12) THEN
SET V_END_HOUR = V_END_HOUR -12;
 END IF;
SET V_END_MINUTE = P_START_MINUTE + P_DURATION_MINUTE;
IF (V_END_MINUTE > 59) THEN
  SET V_END_HOUR_COUNT = 1;
  SET V_END_MINUTE = 60 - V_END_MINUTE;
 /* SET V_END_HOUR = V_END_HOUR+ V_END_HOUR_COUNT;*/
 END IF;
 
  IF V_END_HOUR = 12  THEN
  SET V_END_HOUR =  V_END_HOUR+ V_END_HOUR_COUNT+V_END_HOUR_COUNT;
  ELSE
  SET V_END_HOUR = V_END_HOUR+ V_END_HOUR_COUNT;
   END IF;
  
  IF (P_START_FORMAT = 'am' AND V_END_HOUR < 12) THEN
  SET V_END_FORMAT = 'am';
    ELSE
  SET V_END_FORMAT = 'pm';
   END IF;
   
   SET V_ENDTIME = CONCAT(V_END_HOUR,':',V_END_MINUTE,' ',V_END_FORMAT);
  
SELECT COALESCE(MAX(`id`)+1,1) INTO V_FORGOTPW_COUNT  FROM `test_schedule`;
SELECT `FN_OEP_ID_FORMAT`(9,V_FORGOTPW_COUNT) INTO V_SCHEDULE_START_FORMAT;
SET V_SCHEDULE_START_FORMAT = CONCAT(V_SCHEDULE_START_FORMAT,V_FORGOTPW_COUNT);
SELECT COUNT(*) INTO V_USER_COUNT FROM  `test_schedule` WHERE `ques_master_id` = P_QUESTION_ID AND `testdate` = P_TEST_DATE  
    AND `batch` = P_BATCH;
    IF V_USER_COUNT = 0 THEN
    
    INSERT INTO `test_schedule`
    (`ques_master_id`,`is_test_started`,`test_schedule_id`,`batch`,`testdate`,`start_time`,`end_time`,
    `created_by`,`created_at`,`administrated_by`,`administrated_at`,`status`)VALUES
    (P_QUESTION_ID,P_TEST_STARTED,V_SCHEDULE_START_FORMAT,P_BATCH,P_TEST_DATE,V_STARTTIME,V_ENDTIME,
     P_CREATED_BY,NOW(),P_ADMINISTRATED_BY,NOW(),P_STATUS);
    
    SELECT `id` INTO V_ID  FROM  `test_schedule` WHERE `ques_master_id` = P_QUESTION_ID AND `testdate` = P_TEST_DATE  
    AND `batch` = P_BATCH;
SET out_genrate_id = V_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Test Schedule saved Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Test Schedule Exists';
END IF;
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Test Schedule already Exists';
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
SET V_STARTTIME = CONCAT(P_START_HOUR,':',P_START_MINUTE,' ',P_START_FORMAT);
  SET V_END_HOUR_COUNT = 0;
INSERT INTO `debug_table`(`VALUE1`)VALUES(V_STARTTIME);
SET V_END_HOUR = P_START_HOUR + P_DURATION_HOUR;
SET V_END_MINUTE = P_START_MINUTE + P_DURATION_MINUTE;
IF (P_START_FORMAT = 'am' AND V_END_HOUR > 12) THEN
SET V_END_HOUR = V_END_HOUR -12;
 END IF;
IF (V_END_MINUTE > 59) THEN
  SET V_END_HOUR_COUNT = 1;
  SET V_END_MINUTE = 60 - V_END_MINUTE; 
 END IF;
 
 
 IF V_END_HOUR = 12  THEN
  SET V_END_HOUR = V_END_HOUR+ V_END_HOUR_COUNT;
  ELSE
  SET V_END_HOUR = V_END_HOUR+ V_END_HOUR_COUNT;
   END IF;
   
   
  IF (P_START_FORMAT = 'am' AND V_END_HOUR < 12) THEN
  SET V_END_FORMAT = 'am';
  ELSE
  SET V_END_FORMAT = 'pm';
   END IF;
   
   SET V_ENDTIME = CONCAT(V_END_HOUR,':',V_END_MINUTE,' ',V_END_FORMAT);
   INSERT INTO `debug_table`(`VALUE3`)VALUES(V_ENDTIME);
SELECT COUNT(*) INTO V_USER_COUNT FROM `test_schedule` WHERE `id` = P_TS_ID;
IF V_USER_COUNT = 1 THEN
SELECT COALESCE(COUNT(*),0) INTO V_TOTAL_QUESTIONS FROM `test_schedule` a JOIN 
`question_details` b ON a.`ques_master_id` = b.`ques_master_id`  WHERE a.`id` = P_TS_ID;
UPDATE `test_schedule`
SET 
     `batch` = P_BATCH,
      `ques_master_id` = P_QUESTION_ID,
     `is_test_started`= P_TEST_STARTED,
     `testdate` = P_TEST_DATE,
     `start_time`= V_STARTTIME,
     `end_time`= V_ENDTIME,
      `status`= P_STATUS,
      `administrated_by`= P_ADMINISTRATED_BY,
      /*`created_at`=NOW(), */
      `administrated_at`= NOW(),
      /*`created_by`= P_CREATED_BY,*/
      `total_questions` = V_TOTAL_QUESTIONS 
     WHERE `id` = P_TS_ID; 
     
SET out_result_type = 'S';      
SET out_result_msg = 'Test Started Successfully';
ELSE
SET out_genrate_id = P_QUESTION_ID;
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `test_schedule` WHERE `id` = P_TS_ID;
IF V_USER_COUNT = 1 THEN
DELETE FROM `test_schedule`  WHERE `id` = P_TS_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Test Schedule deleted Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SEND_ADMIN_COURSE_CERTIFICATE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SEND_ADMIN_COURSE_CERTIFICATE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SEND_ADMIN_COURSE_CERTIFICATE`(
		IN P_CONTENT VARCHAR(512),	
		IN P_SUBJECT VARCHAR(512),	
		IN P_MAIL_ID VARCHAR(512),				
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),		
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_MAIL VARCHAR(512);
DECLARE V_IMH_ID VARCHAR(512);
IF P_OPRN = 'INS' THEN
SELECT MAX(COALESCE(`imh_id`,0))+1  INTO V_IMH_ID FROM `email_history`;
INSERT INTO `email_history`
(
`imh_id`,`imh_email_id`,`imh_email_content`,`imh_email_subject`,`imh_email_created_at`,`imh_staus`) VALUES
(V_IMH_ID,P_MAIL_ID,P_CONTENT,P_SUBJECT,NOW(),0);
SET out_genrate_id = V_IMH_ID;
SET out_result_type = 'S';      
SET out_result_msg = 'Certificate send Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SEND_PARTICIPANT_COURSE_CERTIFICATE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SEND_PARTICIPANT_COURSE_CERTIFICATE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SEND_PARTICIPANT_COURSE_CERTIFICATE`(
		IN P_DOWNLOAD_LINK TEXT,
		IN P_PARTICIPANT_ID VARCHAR(512),	
		IN P_TS_ID VARCHAR(512),				
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_genrate_facultyid VARCHAR(48),
		OUT out_genrate_adminid VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_MAIL VARCHAR(512);
DECLARE V_FACULTY_MAIL VARCHAR(512);
DECLARE V_ADMIN_MAIL VARCHAR(512);
DECLARE V_IMH_ID VARCHAR(512);
DECLARE V_IMH_FACID VARCHAR(512);
DECLARE V_IMH_ADMINID VARCHAR(512);
DECLARE V_MAILCONTENT TEXT;
IF P_OPRN = 'INS' THEN
SELECT `email_id` INTO V_MAIL FROM `participants` WHERE `participant_id` = P_PARTICIPANT_ID;
SELECT `email` INTO V_FACULTY_MAIL FROM `test_schedule`a JOIN `course_scheduling`b ON a.`batch`=b.`cs_id`
JOIN `faculty_master`c ON c.`faculty_id`=b.`faculty_name` WHERE a.`id` = P_TS_ID;
/*SELECT `email` INTO V_ADMIN_MAIL FROM `users`  WHERE `userid`= 1;*/
SELECT `email_temp_content` INTO V_MAILCONTENT FROM `email_template` WHERE `email_temp_id` = 6;
SET V_MAILCONTENT = REPLACE(V_MAILCONTENT,'param1',P_DOWNLOAD_LINK);
SELECT MAX(coalesce(`imh_id`,0))+1  INTO V_IMH_ID FROM `email_history`;
INSERT INTO `email_history`
(
`imh_id`,`imh_email_id`,`imh_email_content`,`imh_email_subject`,`imh_email_created_at`,`imh_staus`) VALUES
(V_IMH_ID,V_MAIL,V_MAILCONTENT,'sendmail',NOW(),0);
SELECT MAX(COALESCE(`imh_id`,0))+1  INTO V_IMH_FACID FROM `email_history`;
INSERT INTO `email_history`
(
`imh_id`,`imh_email_id`,`imh_email_content`,`imh_email_subject`,`imh_email_created_at`,`imh_staus`) VALUES
(V_IMH_FACID,V_FACULTY_MAIL,V_MAILCONTENT,'sendmail',NOW(),0);
/*
SELECT MAX(COALESCE(`imh_id`,0))+1  INTO V_IMH_ADMINID FROM `email_history`;
INSERT INTO `email_history`
(
`imh_id`,`imh_email_id`,`imh_email_content`,`imh_email_subject`,`imh_email_created_at`,`imh_staus`) VALUES
(V_IMH_ADMINID,V_ADMIN_MAIL,V_MAILCONTENT,'sendmail',NOW(),0);*/
SET out_genrate_id = V_IMH_ID;
SET out_genrate_facultyid = V_IMH_FACID;
/*SET out_genrate_adminid = V_IMH_ADMINID;*/
SET out_result_type = 'S';      
SET out_result_msg = 'Certificate send Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SIGN_UP` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SIGN_UP` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SIGN_UP`(
		IN P_USERNAME VARCHAR(512),
		IN P_USERID VARCHAR(512),
		IN P_EMAIL VARCHAR(512),
		IN P_MOBILE VARCHAR(512),
		IN P_PASSWORD VARCHAR(512),
		IN P_CATEGORY VARCHAR(512),		
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_COUNT INT;
DECLARE V_FORGOTPW_COUNT INT;
IF P_OPRN = 'INS' THEN
SELECT COUNT(*) INTO V_COUNT FROM `users` WHERE `username` = P_USERNAME AND `role` = 5;
IF V_COUNT = 0 THEN
INSERT INTO `users`
(
`username`,`role`,`email`,`phone_no`,`password`,`ps_number`)VALUES
(P_USERNAME,'5',P_EMAIL,P_MOBILE,P_PASSWORD,P_CATEGORY);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'Registered  Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = ' Name already exists';
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `users` WHERE `userid` = P_USERID;
IF V_USER_COUNT = 1 THEN
UPDATE `users`
SET `username` = P_USERNAME,
     `email`= P_EMAIL,
      `phone_no`= P_MOBILE,
      `password`= P_PASSWORD,
      `ps_number` = P_CATEGORY
     WHERE `userid` = P_USERID;
SET out_result_type = 'S';      
SET out_result_msg = ' Details updated Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `users` WHERE `userid` = P_USERID;
IF V_USER_COUNT = 1 THEN
UPDATE `users`   SET  `status` = 0 WHERE `userid` = P_USERID;
SET out_result_type = 'S';      
SET out_result_msg = 'Details deleted Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_SINGLE_PARTICIPANT_CERTIFICATE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_SINGLE_PARTICIPANT_CERTIFICATE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_SINGLE_PARTICIPANT_CERTIFICATE`(
		IN P_PARTICIPANT_ID INT,
		IN P_BATCH VARCHAR(512),
		IN P_RANDOM_NO INT,
		IN P_TS_ID INT,
		IN P_OPRN VARCHAR(24),		
		OUT out_genrate_id VARCHAR(512),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512) 
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_EMAIL_ID VARCHAR(512);
DECLARE V_TEST_SCHEDULE_ID VARCHAR(512);
DECLARE V_QUESTION_ID VARCHAR(512);
IF P_OPRN = 'INS' THEN
SELECT `test_schedule_id`,`ques_master_id` INTO V_TEST_SCHEDULE_ID,V_QUESTION_ID FROM `test_schedule`
WHERE `id` = P_TS_ID;
INSERT INTO `part_certificate_dtls`
(`part_id`,`batch`,`test_id`,`ques_master_id`,`ts_id`,`random_no`)VALUES
(P_PARTICIPANT_ID,P_BATCH,V_TEST_SCHEDULE_ID,V_QUESTION_ID,P_TS_ID,P_RANDOM_NO);
SET out_genrate_id = P_RANDOM_NO;
SET out_result_type = 'S';      
SET out_result_msg = 'Details saved Successfully';
elseIF P_OPRN = 'SEND_MAIL' THEN
SELECT `email_id` INTO V_EMAIL_ID FROM `participants` WHERE `participant_id` = P_PARTICIPANT_ID;
INSERT INTO `part_certificate_dtls`
(`part_id`,`batch`,`test_id`,`ques_master_id`,`ts_id`,`random_no`)VALUES
(P_PARTICIPANT_ID,P_BATCH,V_TEST_SCHEDULE_ID,V_QUESTION_ID,P_TS_ID,P_RANDOM_NO);
SET out_genrate_id = P_RANDOM_NO;
SET out_result_type = 'S';      
SET out_result_msg = V_EMAIL_ID;
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_TO_DO_LIST` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_TO_DO_LIST` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_TO_DO_LIST`(
		IN P_CONTENT text,
		IN P_LIST_ID int,
		IN P_USER_ID INT,					
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_COUNT INT;
IF P_OPRN = 'INS' THEN
INSERT INTO `todo_list`
(`content`,`created_by`,`created_at`)VALUES(P_CONTENT,P_USER_ID,NOW());
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'DETAILS SAVED SUCCESSFULLY';
ELSEIF P_OPRN = 'DEL' THEN 
DELETE FROM `todo_list` WHERE `id` = P_LIST_ID;
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = 'DETAILS DELETED SUCCESSFULLY';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = ' INVALID';
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_UPDATE_QUESTION` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_UPDATE_QUESTION` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_UPDATE_QUESTION`(
IN P_CELL1 INT,
IN P_CELL2 TEXT,/*QUESTION*/
IN P_CELL3 VARCHAR(512),/*QUESTION TYPE*/
IN P_CELL4 TEXT,/*OPTIONS*/
IN P_CELL5 TEXT,/*OPTIONS*/
IN P_CELL6 TEXT,/*OPTIONS*/
IN P_CELL7 TEXT,/*OPTIONS*/
IN P_CELL8 TEXT,/*ANSWER*/
IN P_CELL9 VARCHAR(512),/*IMAGE*/
IN P_CELL10 INT,/*MARK*/
IN P_CELL11 INT,/* FILESEQ*/
IN P_CELL12 INT,/* USERID*/
IN P_CELL13 INT,/* QUESTIONID*/
IN P_CELL14 INT,/* OPRN*/
IN P_INDEX INT
)
BEGIN
DECLARE V_SEQ_COUNT INT;
DECLARE V_QUESTION_ID INT;
DECLARE out_genrate_id VARCHAR(48);
DECLARE out_result_type VARCHAR(48);
DECLARE o_out_msg VARCHAR(128);
SELECT COUNT(*) INTO V_SEQ_COUNT FROM `question_details`WHERE `question` = P_CELL2 and `question_id` !=P_CELL11;
IF V_SEQ_COUNT = 0 THEN
UPDATE `question_details`
SET `question` = P_CELL2,`question_type`=P_CELL3,`option_1`=P_CELL4,`option_2`= P_CELL5,`option_3`=P_CELL6,`option_4`=P_CELL7,
`answer`=P_CELL8,`image`=P_CELL9,`mark`=P_CELL10 WHERE `ques_master_id`= P_CELL14 AND `question_id` = P_CELL11;
SET out_result_type = "Success";		     
SET o_out_msg = 'Question Updated Successfully';
ELSE
SET out_result_type = "Fail";
SET o_out_msg = 'Question Already Exists';
END IF;
SELECT `question_id` INTO V_QUESTION_ID FROM `question_details` WHERE `question`= P_CELL2;
INSERT INTO `oep_question_import_status` (`seq_no`,`status`,`question_id`,`error_msg`,`uploaded_by`,`record_seq_no`)
VALUES(P_CELL12,out_result_type,V_QUESTION_ID,o_out_msg,P_CELL13,(P_INDEX));
END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_UPDATE_QUESTION_DETAILS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_UPDATE_QUESTION_DETAILS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_UPDATE_QUESTION_DETAILS`(
		IN P_SNO INT,
		IN P_QUESTION TEXT,
		IN P_TYPE VARCHAR(512),
		IN P_OPTION1 TEXT,
		IN P_OPTION2 TEXT,
		IN P_OPTION3 TEXT,	
		IN P_OPTION4 TEXT,
		IN P_ANSWER TEXT,	
		/*IN P_IMAGE VARCHAR(512),	*/	
		IN P_MARK INT(48),
		IN P_QUESTION_ID INT(11),
		IN P_OPRN VARCHAR(24),
		IN P_QUESTIONMASTER_ID INT(11)
		/*OUT out_genrate_id VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512)  */
    )
BEGIN
DECLARE V_QUESTION_ID VARCHAR(512);
IF P_OPRN = 'UPD' THEN
SELECT COUNT(*) INTO V_QUESTION_ID FROM `question_details`
WHERE `question_id` = P_QUESTION_ID;
IF V_QUESTION_ID = 1 THEN 
UPDATE `question_details`
SET  `question`=P_QUESTION ,
     `ques_master_id`=P_QUESTIONMASTER_ID,
     `question_type`=P_TYPE,
     `option_1`=P_OPTION1,
     `option_2`=P_OPTION2,
     `option_3`=P_OPTION3,
     `option_4`=P_OPTION4,
     `answer`=P_ANSWER,
     `mark`=P_MARK,
     `created_date`=NOW(),
     `status`=1
     WHERE `question_id`= P_QUESTION_ID;
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_EXAMPORTAL_USER_MODULE` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_EXAMPORTAL_USER_MODULE` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_EXAMPORTAL_USER_MODULE`(
		IN P_USERNAME VARCHAR(512),
		IN P_USERID VARCHAR(512),
		IN P_EMAIL VARCHAR(512),
		IN P_PASSWORD VARCHAR(512),
		IN P_ENCRYPT_PASSWORD VARCHAR(512),
		IN P_APPLICABLE_IC VARCHAR(512),
		IN P_PS_NUMBER VARCHAR(512),
		IN P_ROLE INT,
		IN P_STATUS VARCHAR(24),
		IN P_OPRN VARCHAR(512),
		OUT out_genrate_id VARCHAR(48),
		OUT out_genrate_mailid VARCHAR(48),
		OUT out_result_type VARCHAR(1),
		OUT out_result_msg VARCHAR(512),
		OUT out_result_msgs VARCHAR(512)  
    )
BEGIN
DECLARE V_USER_COUNT INT;
DECLARE V_COUNT INT;
DECLARE V_IMH_ID  VARCHAR(512);
DECLARE V_USERID INT;
DECLARE V_FORGOTPW_COUNT INT;
DECLARE V_MAIL_CONTENT VARCHAR (512);
IF P_OPRN = 'INS' THEN
SELECT COUNT(*) INTO V_COUNT FROM `users` WHERE `username` = P_USERNAME and `role` = P_ROLE;
IF V_COUNT = 0 THEN
INSERT INTO `users`
(
`username`,
`role`,
`ps_number`,
`app_ic`,
`email`,
`password`,
`status`,
`created_at`
)VALUES
(
P_USERNAME,
P_ROLE,
P_PS_NUMBER,
P_APPLICABLE_IC,
P_EMAIL,
P_ENCRYPT_PASSWORD,
P_STATUS,
now() 
);
SELECT `userid` INTO V_USERID FROM `users` WHERE `username` = P_USERNAME;
IF P_ROLE = 2 THEN 
INSERT INTO `faculty_master`(`userid`,`username`,`password`,`applicable_ic`,`email`,`status`)VALUES
( V_USERID,P_USERNAME,P_PASSWORD,P_APPLICABLE_IC,P_EMAIL,P_STATUS);
SELECT `email_temp_content` INTO V_MAIL_CONTENT FROM `email_template` WHERE `email_temp_id` = 1;
SET V_MAIL_CONTENT = REPLACE(V_MAIL_CONTENT,'$param1',P_PASSWORD);
SELECT MAX(`imh_id`)+1  INTO V_IMH_ID FROM`email_history`;
INSERT INTO `email_history`
(`imh_id`,`imh_email_id`,`imh_email_subject`,`imh_email_content`,`imh_email_created_at`,`imh_staus`,`imh_retry_count`)
VALUES
(V_IMH_ID,P_EMAIL,'newuser',V_MAIL_CONTENT,NOW(),0,0);
SET out_genrate_id = V_USERID;
SET out_genrate_mailid = V_IMH_ID;
SET out_result_type = 'S';      
SET out_result_msg = "User Registered Successfully.Password will be sent to Registered Mail. ";
ELSE
SET out_genrate_id = V_USERID;
SET out_result_type = 'S';      
SET out_result_msg = 'User Registered Successfully';
END IF;
END IF;
END IF;
IF P_OPRN = 'UPD' THEN
UPDATE `users`
SET `username` = P_USERNAME,
     /*`password`= P_ENCRYPT_PASSWORD,*/
     `role` = P_ROLE,
     `app_ic`= P_APPLICABLE_IC,
     `ps_number` = P_PS_NUMBER,
      `email`= P_EMAIL,     
      `status` = P_STATUS
     WHERE `userid` = P_USERID;
SELECT COUNT(*) INTO V_USER_COUNT FROM `faculty_master` WHERE `username` = P_USERNAME;
IF V_USER_COUNT = 1 and P_ROLE = 2 THEN
UPDATE `faculty_master`
SET `username` = P_USERNAME,
    /*`password`= P_ENCRYPT_PASSWORD,*/
      `email`= P_EMAIL
     
     WHERE `username` = P_USERNAME;
SET out_result_type = 'S';      
SET out_result_msg = "User details updated sucessfully.";
ELSE
INSERT INTO `faculty_master`(`userid`,`username`,`password`,`email`,`status`)VALUES( P_USERID,P_USERNAME,P_PASSWORD,P_EMAIL,P_STATUS);
SET out_genrate_id = 1;
SET out_result_type = 'S';      
SET out_result_msg = "User details updated sucessfully.";
END IF;
if P_ROLE != 2 then 
delete from `faculty_master`  WHERE `userid` = P_USERID;
SET out_result_type = 'S';      
SET out_result_msg = "User updated  sucessfully";
END IF;
ELSEIF P_OPRN = 'DEL' THEN
SELECT COUNT(*) INTO V_USER_COUNT FROM `users` WHERE `userid` = P_USERID;
IF V_USER_COUNT = 1 THEN
UPDATE  `users`  SET `status` = '0'  WHERE `userid` = P_USERID;
SET out_result_type = 'S';      
SET out_result_msg = 'USER details DELETED Successfully';
ELSE
SET out_genrate_id = 0;
SET out_result_type = 'F';      
SET out_result_msg = 'Invalid data';
END IF;
END IF;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_OEP_SAVE_QUESTION_HISTORY` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_OEP_SAVE_QUESTION_HISTORY` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_OEP_SAVE_QUESTION_HISTORY`(
		in P_TEST_ID int,
		IN P_QUESTION_ID int,
		IN P_USER_ID int,
		IN P_DATE DATE,
		IN P_QUESTION_ANSWER text,		
		IN P_TIME VARCHAR(512),
		IN P_QUESTION_STATUS VARCHAR(11),
		in P_NEXT_QUESTION_ID int,
		in P_OPRN varchar(128),
		out out_generate_id varchar(11)
    )
BEGIN
DECLARE AVL_COUNT INT DEFAULT 0;
if P_OPRN = "CONTINUOUS_QUESTION" then
SELECT COALESCE(COUNT(*),0) INTO AVL_COUNT FROM `test_question_history` 
WHERE `question_id` = P_NEXT_QUESTION_ID AND `ts_id` = P_TEST_ID AND `user_id` = P_USER_ID;
IF P_NEXT_QUESTION_ID != 0 THEN
IF AVL_COUNT = 0 THEN
/*DELETE  FROM `test_question_history` WHERE `question_id`=P_QUESTION_ID
AND `shedule_id`=P_SCHEDULE_ID AND `part_id`=P_PART_ID AND `question_name`=P_QUESTION_NAME;*/
INSERT INTO `test_question_history`(
`question_id`,
`ts_id`,
`user_id`,
`created_date`,
`start_time`,
`end_time`,
`question_status`
)VALUES
(
P_NEXT_QUESTION_ID,
P_TEST_ID,
P_USER_ID,
P_DATE,
P_TIME,
"",
P_QUESTION_STATUS
);
ELSE
UPDATE `test_question_history` SET 
`created_date`=P_DATE,
`start_time` = P_TIME,
`question_status` = P_QUESTION_STATUS,
`end_time` = ""
 WHERE `question_id`=P_NEXT_QUESTION_ID AND `ts_id`=P_TEST_ID AND `user_id` = P_USER_ID; 
END IF;
END IF;
UPDATE `test_question_history` SET 
`question_answer`=P_QUESTION_ANSWER,
`created_date`=P_DATE,
`end_time` = P_TIME,
`question_status` = "1"
WHERE `question_id`=P_QUESTION_ID AND `ts_id`=P_TEST_ID AND `user_id` = P_USER_ID;
ELSEIF P_OPRN = "FIRST_QUESTION" THEN
UPDATE `test_participants` SET `started_at` = NOW(),`is_participant_start`= 1 WHERE `ts_id`= P_TEST_ID AND `userid`= P_USER_ID;
INSERT INTO `test_question_history`(
`question_id`,
`ts_id`,
`user_id`,
`created_date`,
`start_time`,
`question_status`
)VALUES
(
P_NEXT_QUESTION_ID,
P_TEST_ID,
P_USER_ID,
CURDATE(),
DATE_FORMAT(NOW(), "%H:%i"),
P_QUESTION_STATUS
);
end if;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_OEP_SCHEDULED_TEST_PARTICIPANTS` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_OEP_SCHEDULED_TEST_PARTICIPANTS` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_OEP_SCHEDULED_TEST_PARTICIPANTS`(
IN P_TEST_ID INT,
IN P_PART_ID INT,
IN P_USER_ID INT,
IN P_URL VARCHAR(512),
IN P_OPRN VARCHAR(12)
    )
BEGIN
DECLARE V_CATEGORY_ID INT;
DECLARE V_QUESTION_COUNT INT;
DECLARE V_COUNT INT;
DECLARE V_EMAIL_ID VARCHAR(512);
DECLARE V_QUESTION_MASTER_ID INT;
declare V_PART_MARK int default 0;
DECLARE V_TOTAL_MARK INT DEFAULT 0;
declare V_ATTEMPTED_PART int default 0;
declare V_RESULT int default 0;
IF P_OPRN = 'INS' THEN
DELETE FROM `test_participants` WHERE `part_id` = P_PART_ID and `ts_id`= P_TEST_ID;
SELECT COUNT(*) into V_QUESTION_COUNT FROM `question_details` a JOIN `question_master` b ON a.`ques_master_id`=b.`id` JOIN `test_schedule` c
ON c.`ques_master_id`=b.`id` WHERE c.`id`=P_TEST_ID;
INSERT INTO `test_participants`
(
`ts_id`,`part_id`,`userid`,`question_count`
)
VALUES(
P_TEST_ID,P_PART_ID,P_USER_ID,V_QUESTION_COUNT
);
SELECT `email` INTO V_EMAIL_ID FROM `users` WHERE `userid` =  P_USER_ID;
/* INSERT INTO `email_history`
(`imh_email_id`,`imh_email_subject`,`imh_email_content`,`imh_email_created_at`,`imh_staus`,`ts_id`,`part_id`)
VALUES(V_EMAIL_ID,"Test Request",concat("You have received a Test Request . Please click on the link  to start the test.",
P_URL),NOW(),0,P_TEST_ID,P_PART_ID); */
ELSEIF P_OPRN = 'UPD_MARK' THEN
if P_PART_ID > 0 and P_TEST_ID > 0 then
SELECT COALESCE(SUM(`ind_ques_mark`),0) into V_PART_MARK FROM `test_participants` a JOIN `part_ques_details` b ON a.`id` = b.`part_test_id` WHERE
a.`userid` = P_PART_ID AND a.`ts_id` = P_TEST_ID;
SELECT COALESCE(SUM(`mark`),0) into V_TOTAL_MARK FROM `test_schedule` a JOIN `question_details` b ON a.`ques_master_id` = b.`ques_master_id` 
WHERE a.`id` = P_TEST_ID;
SELECT COALESCE(`total_participant_attempted`,0)+ 1 into V_ATTEMPTED_PART  FROM `test_schedule` WHERE `id` =  P_TEST_ID;
/* calculate pass or fail with mark greater than 5% */
SELECT V_PART_MARK >= (V_TOTAL_MARK*(5/100)) into V_RESULT;
update `test_participants` set
`full_mark` = V_TOTAL_MARK,
`total_mark` = V_PART_MARK,
`is_pass` = V_RESULT,
`is_participant_start` = 2,
`end_time` = Now()
where `userid` = P_PART_ID and `ts_id` = P_TEST_ID;
update `test_schedule` set
`total_participant_attempted` = V_ATTEMPTED_PART 
where `id` = P_TEST_ID;
INSERT INTO `part_final_mark_response` 
(`flag`,`response_msg`,`part_id`,`schedule_id`,`part_mark`,`full_mark`,`is_pass`)
VALUES
("S", "You have Successfully Submit the Exam. Your Score is",P_PART_ID,P_TEST_ID,V_PART_MARK,V_TOTAL_MARK,V_RESULT);
else
INSERT INTO `part_final_mark_response` 
(`flag`,`response_msg`,`part_id`,`schedule_id`,`part_mark`,`full_mark`)
VALUES
("F", "Error",P_PART_ID,P_TEST_ID,V_PART_MARK,V_TOTAL_MARK);
end if;
end if;
    END */$$
DELIMITER ;

/* Procedure structure for procedure `P_OEP_TOKEN_MASTER` */

/*!50003 DROP PROCEDURE IF EXISTS  `P_OEP_TOKEN_MASTER` */;

DELIMITER $$

/*!50003 CREATE DEFINER=`root`@`%` PROCEDURE `P_OEP_TOKEN_MASTER`(
IN P_TOKEN_ID INT,
IN P_TOKEN_KEY VARCHAR(128),
IN P_TOKEN_LOGINOUT_STATUS INT,
IN P_TOKEN_UD_USERID INT,
IN P_TOKEN_UD_USERNAME VARCHAR(128),
IN P_TOKEN_UD_ROLE INT,
IN P_OPRN VARCHAR(258),
OUT out_genrate_id VARCHAR(48),
OUT out_result_type VARCHAR(1),
OUT out_result_msg VARCHAR(48)
)
BEGIN
DECLARE V_SEQ_COUNT INT;
DECLARE V_TOKEN_COUNT INT;
IF P_OPRN = 'INS' THEN
       SELECT COUNT(*) INTO V_SEQ_COUNT FROM `token_seq`;
       IF V_SEQ_COUNT = 0 THEN
       SET out_genrate_id = 1;
       INSERT INTO `token_seq` VALUES(out_genrate_id);
       ELSE
       SELECT MAX(SEQ_ID)+1 INTO out_genrate_id FROM `token_seq`;
       UPDATE `token_seq` SET SEQ_ID = out_genrate_id;
       END IF;
SELECT COUNT(*) INTO V_TOKEN_COUNT FROM `token` WHERE `token_key` = P_TOKEN_KEY;
IF V_TOKEN_COUNT = 0 THEN
INSERT INTO `token` 
	(
		`token_id`,
		`token_key`,
		`token_loginout_status`,
		`token_ud_userid`,
		`token_ud_username`,		
		`token_ud_role`,		
		`token_in_time`
	)
	VALUES
	(
	out_genrate_id,
	P_TOKEN_KEY,
	P_TOKEN_LOGINOUT_STATUS,
	P_TOKEN_UD_USERID,
	P_TOKEN_UD_USERNAME,	
	P_TOKEN_UD_ROLE,
	NOW()
	);
SET out_result_type = 'S';      
SET out_result_msg = 'Token Created Successfully';
ELSE
SET out_result_type = 'F';      
SET out_result_msg = 'Token Already Exists';
END IF;       
ELSEIF P_OPRN = 'UPD' THEN
SET out_genrate_id = P_TOKEN_ID;
SELECT COUNT(*) INTO V_TOKEN_COUNT FROM `token` WHERE `token_key` = P_TOKEN_KEY;
IF V_TOKEN_COUNT != 0 THEN
       UPDATE `token` SET 
       `token_loginout_status` = P_TOKEN_LOGINOUT_STATUS , 
       `token_out_time` = NOW()
       WHERE `token_key` = P_TOKEN_KEY;
SET out_result_type = 'S';      
SET out_result_msg = 'Status Updated Successfully';
ELSE
SET out_result_type = 'F';      
SET out_result_msg = 'History not Available. Try again!!';
END IF;
END IF;   
    END */$$
DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
